package com.postgresql.weddingSalon.Entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "companytrackerdata") // Replace with the name of your table
public class companyData {

    @Column(name = "lead_date")
    private LocalDate leadDate;
    @Column(name = "last_call")
    private LocalDate lastCall;
    @Column(name = "last_follow_up")
    private LocalDate lastFollowUp;
    private String rep;
    private String origin;
    private String company;
    private String category;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String title;
    private String priority;
    private String email;
    @Column(name = "last_email_client")
    private LocalDate lastEmailClient;
    private String note;
    @Column(name = "temp_type")
    private String tempType;
    private String status;
    private String ny;
    private String la;
    private String fl;
    private String tx;
    private String il;
    private String dc;
    @Column(name = "last_action")
    private String lastAction;
    @Column(name = "show_outreach")
    private String showOutreach;
    @Column(name = "said_no_to")
    private String saidNoTo;
    @Column(name = "target_client")
    private String targetClient;
    @Column(name = "email_action")
    private String emailAction;
    private String mobile;
    private String office;
    private String address;
    private String city;
    private String zip;
    @Column(name = "social_media")
    private String socialMedia;
    @Column(name = "next_follow_up")
    private LocalDate nextFollowUp;
    @Column(name = "inquiry_status")
    private String inquiryStatus;
    private String website;
    @Column(name = "vendor_tour")
    private Boolean vendorTour;
    @Column(name = "calendly_source")
    private String calendlySource;
    private String recommender;
    @Column(name = "inquiry_origin")
    private String inquiryOrigin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Match this to your primary key column

}
