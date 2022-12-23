package com.example.springbatchtutorial.job.filedatareadwrite.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerYears {

    private String id;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;
    private int yearsExperience;

    public PlayerYears(Player player) {
        this.id = player.getId();
        this.lastName = player.getLastName();
        this.firstName = player.getFirstName();
        this.position = player.getPosition();
        this.birthYear = player.getBirthYear();
        this.debutYear = player.getDebutYear();
        this.yearsExperience = LocalDate.now().getYear() - player.getDebutYear();
    }
}
