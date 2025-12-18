package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoProduct;
import com.murathnakts.dto.DtoProductIU;
import com.murathnakts.entity.Product;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.mapper.Mapper;
import com.murathnakts.repository.ProductRepository;
import com.murathnakts.service.IJwtService;
import com.murathnakts.service.IProductService;
import com.murathnakts.service.ISellerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final IJwtService jwtService;
    private final ISellerService sellerService;

    public ProductServiceImpl(ProductRepository productRepository,
                              IJwtService jwtService,
                              ISellerService sellerService) {
        this.productRepository = productRepository;
        this.jwtService = jwtService;
        this.sellerService = sellerService;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<DtoProduct> getAllProducts() {
        return Mapper.toDtoProduct(productRepository.findAll());
    }

    @Override
    public DtoProduct getProductById(Long id) {
        return Mapper.toDtoProduct(findById(id));
    }

    @Transactional
    @Override
    public DtoProduct createProduct(DtoProductIU dtoProductIU) {
        Product product = new Product();
        product.setName(dtoProductIU.getName());
        product.setDescription(dtoProductIU.getDescription());
        product.setStock(dtoProductIU.getStock());
        product.setCategory(dtoProductIU.getCategory());
        product.setAmount(dtoProductIU.getAmount());
        product.setSeller(sellerService.findById(jwtService.getCurrentUserId()));
        return Mapper.toDtoProduct(productRepository.save(product));
    }

    @Transactional
    @Override
    public DtoProduct updateProduct(Long id, DtoProductIU dtoProductIU) {
        Product product = findById(id);
        product.setName(dtoProductIU.getName());
        product.setDescription(dtoProductIU.getDescription());
        product.setStock(dtoProductIU.getStock());
        product.setCategory(dtoProductIU.getCategory());
        product.setAmount(dtoProductIU.getAmount());
        return Mapper.toDtoProduct(product);
    }

    @Transactional
    @Override
    public Boolean deleteProduct(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        return true;
    }

    @Override
    public void decreaseStock(Product product, Integer count) {
        product.setStock(product.getStock() - count);
        productRepository.save(product);
    }

    @Override
    public void increaseStock(Product product, Integer count) {
        product.setStock(product.getStock() + count);
        productRepository.save(product);
    }
}
