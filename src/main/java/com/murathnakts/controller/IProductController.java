package com.murathnakts.controller;

import com.murathnakts.dto.DtoProduct;
import com.murathnakts.dto.DtoProductIU;
import com.murathnakts.handler.ApiResponse;

import java.util.List;

public interface IProductController {
    ApiResponse<List<DtoProduct>> getAllProducts();
    ApiResponse<DtoProduct> getProductById(Long id);
    ApiResponse<DtoProduct> addProduct(DtoProductIU dtoProduct);
    ApiResponse<DtoProduct> updateProduct(Long id, DtoProductIU dtoProduct);
    ApiResponse<Boolean> deleteProduct(Long id);
}
