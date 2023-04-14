package com.stin.stinprojectmaven.backend.Entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {
    private String toAddress;
    private String subject;
    private String message;

    public void addCodeToMessage(int code) {
        this.message = this.message + " " + code;
    }
}

