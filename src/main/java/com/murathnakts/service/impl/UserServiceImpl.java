package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.Users;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.mapper.Mapper;
import com.murathnakts.repository.UserRepository;
import com.murathnakts.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public DtoUser createUser(DtoUserIU dtoUserIU) {
        Users user = new Users();
        user.setUsername(dtoUserIU.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dtoUserIU.getPassword()));
        return Mapper.toDtoUser(userRepository.save(user));
    }
}
