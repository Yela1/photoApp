package com.appsdevelopingblog.photoapp.api.users.ui.service;

import com.appsdevelopingblog.photoapp.api.users.ui.domain.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userRequestModel);
    UserDto getUserDetailsByEmail(String email);
}
