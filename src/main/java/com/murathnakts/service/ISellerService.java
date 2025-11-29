package com.murathnakts.service;

import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.Seller;

public interface ISellerService {
    Seller findByEmail(String email);
    Seller findById(Long id);
    Seller createSeller(DtoUserIU dtoUserIU);

}
