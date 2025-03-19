package com.ifpb.pwebProject.controller;

import com.ifpb.pwebProject.exceptions.ActivityNotFound;
import com.ifpb.pwebProject.model.Activity;
import com.ifpb.pwebProject.controller.service.ActivityService;
import com.ifpb.pwebProject.model.User;
import com.ifpb.pwebProject.model.dto.ActivityRequestDTO;
import com.ifpb.pwebProject.model.dto.ActivityResponseDto;
import com.ifpb.pwebProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
    @PutMapping
    public ResponseEntity<Object> saveOrUpdate(@RequestBody ActivityRequestDTO activityRequestDTO){
        Activity activity = this.activityService.updateOrSave(activityRequestDTO);
        return ResponseEntity.ok(ActivityResponseDto.from(activity));
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable UUID activityId){
        this.activityService.deleteActity(activityId);
    }

}
