package com.murathnakts.controller;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.handler.ApiResponse;

public interface IAuthController {
    ApiResponse<DtoUser> getUserById(Long id);
    ApiResponse<DtoUser> register(DtoUserIU dtoUserIU);
}
