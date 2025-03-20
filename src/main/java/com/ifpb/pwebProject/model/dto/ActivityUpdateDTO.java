package com.ifpb.pwebProject.model.dto;

import com.ifpb.pwebProject.model.Activity;
import com.ifpb.pwebProject.model.User;

import java.time.LocalDate;
import java.util.UUID;

public record ActivityUpdateDTO(UUID id, String userID, String title, String description, LocalDate date, String hour, String address, String clientNumber, String clientName, double price, boolean done, double pricePayed, boolean paied) {
    public Activity toEntity(User user) {
        return new Activity(
                this.id,
                user,
                this.title,
                this.description,
                this.date,
                this.hour,
                this.address,
                this.clientNumber,
                this.clientName,
                this.price,
                this.done,
                this.pricePayed,
                this.paied
        );
    }
}
