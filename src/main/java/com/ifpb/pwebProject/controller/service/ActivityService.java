package com.ifpb.pwebProject.controller.service;

import com.ifpb.pwebProject.exceptions.ActivityNotFound;
import com.ifpb.pwebProject.exceptions.UserNotFound;
import com.ifpb.pwebProject.model.Activity;
import com.ifpb.pwebProject.model.User;
import com.ifpb.pwebProject.model.dto.ActivityRequestDTO;
import com.ifpb.pwebProject.repository.ActivityRepository;
import com.ifpb.pwebProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    public Activity getActivity(UUID activityId){
        Optional<Activity> activity = activityRepository.findById(activityId);
        if (activity.isEmpty()){
            throw new ActivityNotFound("Atividade não encontrada");
        }
        return activity.get();
    }

    public Activity updateOrSave(ActivityRequestDTO activityRequestDTO){
        Optional<User> user = userRepository.findById(activityRequestDTO.userId());
        if (user.isEmpty()){
            throw new UserNotFound("Usuário não encontrado");
        }
        Activity activity = activityRequestDTO.toEntity(user.get());
       return this.activityRepository.save(activity);
    }

    public void deleteActity(UUID id){
        this.activityRepository.deleteById(id);
    }
}
