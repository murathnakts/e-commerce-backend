package com.murathnakts.service;

import com.murathnakts.dto.DtoProduct;
import com.murathnakts.dto.DtoProductIU;
import com.murathnakts.entity.Product;

import java.util.List;

public interface IProductService {
    Product findById(Long id);
    List<DtoProduct> getAllProducts();
    DtoProduct getProductById(Long id);
    DtoProduct createProduct(DtoProductIU dtoProductIU);
    DtoProduct updateProduct(Long id, DtoProductIU dtoProductIU);
    Boolean deleteProduct(Long id);
    void decreaseStock(Product product, Integer count);
    void increaseStock(Product product, Integer count);
}
