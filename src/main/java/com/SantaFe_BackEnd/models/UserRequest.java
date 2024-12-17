package com.SantaFe_BackEnd.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest implements IUser{
    
    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    private Boolean admin;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    @Override
    public boolean isAdmin() {
        return admin;
    }



}
