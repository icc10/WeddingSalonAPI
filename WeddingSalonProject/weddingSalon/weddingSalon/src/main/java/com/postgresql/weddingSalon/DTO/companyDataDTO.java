package com.postgresql.weddingSalon.DTO;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class companyDataDTO {
    private LocalDate leadDate;
    private LocalDate lastCall;
    private LocalDate lastFollowUp;
    private String rep;
    private String origin;
    private String company;
    private String category;
    private String firstName;
    private String lastName;
    private String title;
    private String priority;
    private String email;
    private LocalDate lastEmailClient;
    private String note;
    private String tempType;
    private String status;
    private String ny;
    private String la;
    private String fl;
    private String tx;
    private String il;
    private String dc;
    private String lastAction;
    private String showOutreach;
    private String saidNoTo;
    private String targetClient;
    private String emailAction;
    private String mobile;
    private String office;
    private String address;
    private String city;
    private String zip;
    private String socialMedia;
    private LocalDate nextFollowUp;
    private String inquiryStatus;
    private String website;
    private Boolean vendorTour;
    private String calendlySource;
    private String recommender;
    private String inquiryOrigin;


    private Long id; // Match this to your primary key column

}
