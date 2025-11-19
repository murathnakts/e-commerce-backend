package com.murathnakts.mapper;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.entity.Users;

import java.util.Collections;
import java.util.List;

public class Mapper {

    public static DtoUser toDtoUser (Users user) {
        if (user == null) return null;
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(user.getId());
        dtoUser.setCreateTime(user.getCreateTime());
        dtoUser.setEmail(user.getEmail());
        dtoUser.setRole(user.getRole());
        return dtoUser;
    }

    public static List<DtoUser> toDtoUser (List<Users> users) {
        if (users == null || users.isEmpty()) return Collections.emptyList();
        return users.stream().map(Mapper::toDtoUser).toList();
    }
}
