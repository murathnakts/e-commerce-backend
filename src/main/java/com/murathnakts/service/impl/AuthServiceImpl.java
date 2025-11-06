package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.User;
import com.murathnakts.repository.UserRepository;
import com.murathnakts.service.IAuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public DtoUser getUserById(Long id) {
        DtoUser dtoUser = new DtoUser();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            dtoUser.setUsername(user.getUsername());
            dtoUser.setPassword(user.getPassword());
            dtoUser.setId(user.getId());
            dtoUser.setCreateTime(user.getCreateTime());
        }
        return dtoUser;
    }

    @Override
    public DtoUser register(DtoUserIU dtoUserIU) {
        User user = new User();
        user.setUsername(dtoUserIU.getUsername());
        user.setPassword(dtoUserIU.getPassword());
        User savedUser = userRepository.save(user);
        DtoUser dtoUser = new DtoUser();
        dtoUser.setUsername(savedUser.getUsername());
        dtoUser.setPassword(savedUser.getPassword());
        dtoUser.setId(savedUser.getId());
        dtoUser.setCreateTime(savedUser.getCreateTime());
        return dtoUser;
    }
}
