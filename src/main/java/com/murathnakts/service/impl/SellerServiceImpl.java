package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoUserIU;
import com.murathnakts.entity.Seller;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.repository.SellerRepository;
import com.murathnakts.service.ISellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements ISellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller findByEmail(String email) {
        return sellerRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public Seller findById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.USER_NOT_FOUND));
    }

    @Override
    public Seller createSeller(DtoUserIU dtoUserIU) {
        Seller seller = new Seller();
        seller.setEmail(dtoUserIU.getEmail());
        seller.setPassword(dtoUserIU.getPassword());
        seller.setRole(dtoUserIU.getRole());
        return sellerRepository.save(seller);
    }
}
