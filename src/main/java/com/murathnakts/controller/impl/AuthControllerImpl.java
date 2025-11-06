package com.murathnakts.controller.impl;

import com.murathnakts.controller.IAuthController;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.handler.ApiResponse;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthControllerImpl implements IAuthController {

    private final IAuthService authService;

    public AuthControllerImpl(IAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/get-user/{id}")
    @Override
    public ApiResponse<DtoUser> getUserById(@PathVariable(value = "id") Long id) {
        return ApiResponse.success(authService.getUserById(id), ResponseMessage.SUCCESS);
    }

    @PostMapping("/register")
    @Override
    public ApiResponse<DtoUser> register(@RequestBody DtoUserIU dtoUserIU) {
        return ApiResponse.success(authService.register(dtoUserIU), ResponseMessage.USER_CREATED);
    }
}
