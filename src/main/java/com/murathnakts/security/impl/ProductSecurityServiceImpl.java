package com.murathnakts.security.impl;

import com.murathnakts.entity.Product;
import com.murathnakts.security.IProductSecurityService;
import com.murathnakts.service.IJwtService;
import com.murathnakts.service.IProductService;
import org.springframework.stereotype.Service;

@Service("ProductSecurityService")
public class ProductSecurityServiceImpl implements IProductSecurityService {

    private final IProductService productService;
    private final IJwtService jwtService;

    public ProductSecurityServiceImpl(IProductService productService,
                                      IJwtService jwtService) {
        this.productService = productService;
        this.jwtService = jwtService;
    }

    @Override
    public Boolean isProductOwner(Long productId) {
        Product product = productService.findById(productId);
        return product.getSeller().getId().equals(jwtService.getCurrentUserId());
    }
}
