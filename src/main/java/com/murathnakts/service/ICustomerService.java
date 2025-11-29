package com.murathnakts.service;

import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.Customer;

public interface ICustomerService {
    Customer findByEmail(String email);
    Customer findById(Long id);
    Customer createCustomer(DtoUserIU dtoUserIU);

}
