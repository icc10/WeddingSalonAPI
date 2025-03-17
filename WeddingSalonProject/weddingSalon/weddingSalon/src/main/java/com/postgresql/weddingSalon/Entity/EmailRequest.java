package com.postgresql.weddingSalon.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
    private String from;
    private String[] to;
    private String subject;
    private String body;
    private String host;
    private int port;
    private String username;
    private String password;
}

