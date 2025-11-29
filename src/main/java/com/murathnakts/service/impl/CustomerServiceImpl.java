package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.Customer;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.repository.CustomerRepository;
import com.murathnakts.service.ICartService;
import com.murathnakts.service.ICustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICartService cartService;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(ICartService cartService,
                               CustomerRepository customerRepository) {
        this.cartService = cartService;
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public Customer createCustomer(DtoUserIU dtoUserIU) {
        Customer customer = new Customer();
        customer.setEmail(dtoUserIU.getEmail());
        customer.setPassword(dtoUserIU.getPassword());
        customer.setRole(dtoUserIU.getRole());
        customer.setCart(cartService.createCart(customer));
        return customerRepository.save(customer);
    }
}
