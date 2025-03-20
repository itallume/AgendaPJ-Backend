package com.ifpb.pwebProject.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;
    private String title;
    private String description;
    private LocalDate date;
    private String hour;
    private String address;
    private String clientNumber;
    private String clientName;
    private double price;
    private double pricePayed;
    private boolean done ;
    private boolean paied;

    public Activity(User user, String title, String description, LocalDate date, String hour, String address, String clientNumber, String clientName, double price, boolean done, double pricePayed, boolean paid) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.address = address;
        this.clientNumber = clientNumber;
        this.clientName = clientName;
        this.price = price;
        this.done = done;
        this.pricePayed = pricePayed;
        this.paied = paid;
    }

    public Activity(UUID id, User user, String title, String description, LocalDate date, String hour, String address, String clientNumber, String clientName, double price, boolean done, double pricePayed, boolean paid) {
        this.id =id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
        this.address = address;
        this.clientNumber = clientNumber;
        this.clientName = clientName;
        this.price = price;
        this.done = done;
        this.pricePayed = pricePayed;
        this.paied = paid;
    }

    public Activity(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPricePayed() {
        return pricePayed;
    }

    public void setPricePayed(double pricePayed) {
        this.pricePayed = pricePayed;
    }

    public boolean isPaied() {
        return paied;
    }

    public void setPaied(boolean paied) {
        this.paied = paied;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
