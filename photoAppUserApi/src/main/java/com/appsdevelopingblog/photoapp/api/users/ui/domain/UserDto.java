package com.appsdevelopingblog.photoapp.api.users.ui.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 773143584550579047L;
    private String firstName;

    private String lastName;

    private String password;
    private String email;
    private String userId;
    private String encryptedPassword;

}
