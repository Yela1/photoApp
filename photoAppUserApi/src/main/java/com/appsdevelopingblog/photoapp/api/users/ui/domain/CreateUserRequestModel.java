package com.appsdevelopingblog.photoapp.api.users.ui.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestModel {

    @NotNull(message = "first name cannot be null")
    @Size(min=2, message = "first name should be more than 2 characters")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    @Size(min=2, message = "last name should be more than 2 characters")
    private String lastName;

    @NotNull(message = "password cannot be null")
    @Size(min=8, max=16, message = "password 8,16")
    private String password;

    @Email
    @NotNull(message = "email cannot be null")
    private String email;

}
