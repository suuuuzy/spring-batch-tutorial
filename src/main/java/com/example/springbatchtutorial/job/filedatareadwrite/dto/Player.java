package com.example.springbatchtutorial.job.filedatareadwrite.dto;

import lombok.Data;

@Data
public class Player {

    private String id;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;
}
