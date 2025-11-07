package com.murathnakts.service;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.Users;

public interface IUserService {
    Users findByUsername(String username);
    Users findById(Long id);
    boolean isUsernameTaken(String username);
    DtoUser createUser(DtoUserIU dtoUserIU);
}
