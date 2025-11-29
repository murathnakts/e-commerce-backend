package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoUser;
import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.User;
import com.murathnakts.enums.RoleType;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.mapper.Mapper;
import com.murathnakts.repository.UserRepository;
import com.murathnakts.service.ICustomerService;
import com.murathnakts.service.ISellerService;
import com.murathnakts.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final ICustomerService customerService;
    private final ISellerService sellerService;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(ICustomerService customerService,
                           PasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           ISellerService sellerService) {
        this.customerService = customerService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return findByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public Boolean isUserExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public DtoUser createUser(DtoUserIU dtoUserIU) {
            dtoUserIU.setPassword(bCryptPasswordEncoder.encode(dtoUserIU.getPassword()));
        if (RoleType.ROLE_CUSTOMER.equals(dtoUserIU.getRole())) {
            return Mapper.toDtoUser(customerService.createCustomer(dtoUserIU));
        } else if (RoleType.ROLE_SELLER.equals(dtoUserIU.getRole())) {
            return Mapper.toDtoUser(sellerService.createSeller(dtoUserIU));
        } else {
            throw new BaseException(ResponseMessage.ROLE_NOT_FOUND);
        }
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        User user = findByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
