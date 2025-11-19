package com.murathnakts.service;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.Users;

public interface IUserService {
    Users findByEmail(String email);
    Users findById(Long id);
    boolean isEmailTaken(String email);
    DtoUser createUser(DtoUserIU dtoUserIU);
}
