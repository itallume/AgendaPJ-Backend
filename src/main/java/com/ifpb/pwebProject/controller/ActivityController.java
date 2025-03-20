package com.ifpb.pwebProject.controller;

import com.ifpb.pwebProject.exceptions.ActivityNotFound;
import com.ifpb.pwebProject.exceptions.UserNotFound;
import com.ifpb.pwebProject.model.Activity;
import com.ifpb.pwebProject.controller.service.ActivityService;
import com.ifpb.pwebProject.model.User;
import com.ifpb.pwebProject.model.dto.ActivityRequestDTO;
import com.ifpb.pwebProject.model.dto.ActivityResponseDto;
import com.ifpb.pwebProject.repository.UserRepository;
import com.itextpdf.text.DocumentException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getActivityPerId(@PathVariable UUID id){
        try{
            Activity activity = activityService.getActivity(id);
            return ResponseEntity.ok(ActivityResponseDto.from(activity));
        }catch (ActivityNotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ActivityRequestDTO activityRequestDTO){
        try{
            Activity activity = this.activityService.updateOrSave(activityRequestDTO);
            return ResponseEntity.ok(ActivityResponseDto.from(activity));
        }catch (UserNotFound e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody ActivityRequestDTO activityRequestDTO){
        try {
            Activity activity = this.activityService.updateOrSave(activityRequestDTO);
            return ResponseEntity.ok(ActivityResponseDto.from(activity));
        } catch (UserNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable UUID id){
        this.activityService.deleteActivity(id);
    }

    @GetMapping
    public ResponseEntity<Object> getActivityPerDateByUser(@RequestParam String userID, @RequestParam LocalDate date){
        try{
            List<Activity> activities = this.activityService.getActivityPerDate(date, userID);
            return ResponseEntity.ok(activities.stream().map(ActivityResponseDto::from));
        }catch (UserNotFound e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Object> downloadPdf(@RequestParam String userID, @RequestParam LocalDate date) {
        try {
            byte[] pdfBytes = this.activityService.generateReportByMonth(date, userID);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio_"+ date.getMonthValue()+"/"+date.getYear()+".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Erro ao gerar relatório: " + ex.getMessage()));
        }
    }
}
