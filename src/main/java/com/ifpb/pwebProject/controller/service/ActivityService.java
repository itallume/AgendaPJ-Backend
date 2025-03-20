package com.ifpb.pwebProject.controller.service;

import com.ifpb.pwebProject.exceptions.ActivityNotFound;
import com.ifpb.pwebProject.exceptions.UserNotFound;
import com.ifpb.pwebProject.model.Activity;
import com.ifpb.pwebProject.model.User;
import com.ifpb.pwebProject.model.dto.ActivityRequestDTO;
import com.ifpb.pwebProject.repository.ActivityRepository;
import com.ifpb.pwebProject.repository.UserRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{activityId}")
    public Activity getActivity(UUID activityId){
        Optional<Activity> activity = activityRepository.findById(activityId);
        if (activity.isEmpty()){
            throw new ActivityNotFound("Atividade não encontrada");
        }
        return activity.get();
    }

    public Activity updateOrSave(ActivityRequestDTO activityRequestDTO){
        if (activityRequestDTO.userID() == null || activityRequestDTO.userID().isEmpty()){
            throw new UserNotFound("Usuário inválido");
        }
        User user = userRepository.findById(activityRequestDTO.userID())
                .orElse(new User(activityRequestDTO.userID()));

        Activity activity = activityRequestDTO.toEntity(user);
       return this.activityRepository.save(activity);
    }

    public void deleteActivity(UUID id){
        this.activityRepository.deleteById(id);
    }

    public List<Activity> getActivityPerDate(LocalDate date, String userID){
        if (userID.isEmpty()){
            throw new UserNotFound("Usuário inválido");
        }
        return this.activityRepository.getActivtiesPerDateByUser(date, userID);
    }

    public byte[] generateReportByMonth(LocalDate date, String userID) throws DocumentException, IOException {

        List<Activity> activities = this.activityRepository.getActivitiesPerMonthByUser(date.getYear(), date.getMonthValue(), userID);

        if (activities.isEmpty()) {
            System.out.println("Entrei");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma ocorrência encontrada para o mês selecionado.");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, baos);

        document.open();
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph titulo = new Paragraph("Relatório de "+ date.getMonthValue() +"/"+date.getYear()+"\n\n", tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        Map<Integer, List<Activity>> activitiesPerDay = new TreeMap<>();
        for (Activity a : activities) {
            activitiesPerDay.computeIfAbsent(a.getDate().getDayOfMonth(), k -> new ArrayList<>()).add(a);
        }

        Font dayFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font itemFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

        for (int day : activitiesPerDay.keySet()) {
            document.add(new Paragraph("Dia " + day + ":", dayFont));
            for (Activity a :activitiesPerDay.get(day)) {
                document.add(new Paragraph(" - " + a.getTitle() + ": R$ " + String.format("%.2f", a.getPrice()), itemFont));
            }
            document.add(new Paragraph("\n"));
        }
        document.close();

        return baos.toByteArray();
    }
}
