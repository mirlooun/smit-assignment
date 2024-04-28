package com.smit.emailprocessor.entity;

import lombok.Data;

@Data
public class Procedure {
    private Long id;
    private String name;
    private String email;
    private String identityCode;
    private String reason;
    private boolean emailConfirmationSent;

    @Override
    public String toString() {
        return "Procedure{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", identityCode='" + identityCode + '\'' +
                ", reason='" + reason + '\'' +
                ", emailConfirmationSent=" + emailConfirmationSent +
                '}';
    }
}
