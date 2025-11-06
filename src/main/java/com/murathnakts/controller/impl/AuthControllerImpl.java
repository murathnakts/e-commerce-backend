package com.murathnakts.controller.impl;

import com.murathnakts.controller.IAuthController;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
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
    public DtoUser getUserById(@PathVariable(value = "id") Long id) {
        return authService.getUserById(id);
    }

    @PostMapping("/register")
    @Override
    public DtoUser register(@RequestBody DtoUserIU dtoUserIU) {
        return authService.register(dtoUserIU);
    }
}
