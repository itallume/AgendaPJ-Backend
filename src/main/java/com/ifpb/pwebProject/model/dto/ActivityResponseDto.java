package com.ifpb.pwebProject.model.dto;

import com.ifpb.pwebProject.model.Activity;

import java.time.LocalDate;

public record ActivityResponseDto (
        String id,
        String userID,
        String title,
        String description,
        LocalDate date,
        String hour,
        String address,
        String clientNumber,
        String clientName,
        double price,
        double pricePayed,
        boolean done,
        boolean paied
){
    public static ActivityResponseDto from(Activity activity) {
        return new ActivityResponseDto(
                activity.getId().toString(),
                activity.getUser().getId(),
                activity.getTitle(),
                activity.getDescription(),
                activity.getDate(),
                activity.getHour(),
                activity.getAddress(),
                activity.getClientNumber(),
                activity.getClientName(),
                activity.getPrice(),
                activity.getPricePayed(),
                activity.isDone(),
                activity.isPaied()
        );
    }
}
