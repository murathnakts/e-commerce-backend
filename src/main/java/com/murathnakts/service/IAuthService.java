package com.murathnakts.service;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;

public interface IAuthService {
    DtoUser getUserById(Long id);
    DtoUser register(DtoUserIU dtoUserIU);
}
