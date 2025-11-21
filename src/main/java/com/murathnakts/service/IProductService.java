package com.murathnakts.service;

import com.murathnakts.dto.DtoProduct;
import com.murathnakts.dto.DtoProductIU;
import com.murathnakts.entity.Products;

import java.util.List;

public interface IProductService {
    Products findById(Long id);
    List<DtoProduct> getAllProducts();
    DtoProduct getProductById(Long id);
    DtoProduct createProduct(DtoProductIU dtoProductIU);
    DtoProduct updateProduct(Long id, DtoProductIU dtoProductIU);
    Boolean deleteProduct(Long id);
}
