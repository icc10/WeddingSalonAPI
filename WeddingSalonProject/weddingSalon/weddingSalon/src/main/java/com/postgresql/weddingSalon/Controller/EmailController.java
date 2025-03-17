package com.postgresql.weddingSalon.Controller;

import com.postgresql.weddingSalon.Entity.EmailRequest;
import com.postgresql.weddingSalon.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping()
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(
                emailRequest.getFrom(),
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getBody(),
                emailRequest.getHost(),
                emailRequest.getPort(),
                emailRequest.getUsername(),
                emailRequest.getPassword()
        );
    }
}

