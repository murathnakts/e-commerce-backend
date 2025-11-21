package com.murathnakts.controller.impl;

import com.murathnakts.controller.IProductController;
import com.murathnakts.dto.DtoProduct;
import com.murathnakts.dto.DtoProductIU;
import com.murathnakts.handler.ApiResponse;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControllerImpl implements IProductController {

    private final IProductService productService;

    public ProductControllerImpl(IProductService productService) {
        this.productService = productService;
    }

    //TODO add Pagination, Filter and Sort

    @GetMapping()
    @Override
    public ApiResponse<List<DtoProduct>> getAllProducts() {
        return ApiResponse.success(productService.getAllProducts(), ResponseMessage.SUCCESS);
    }

    @GetMapping("/{id}")
    @Override
    public ApiResponse<DtoProduct> getProductById(@PathVariable Long id) {
        return ApiResponse.success(productService.getProductById(id), ResponseMessage.SUCCESS);
    }

    @PostMapping()
    @Override
    public ApiResponse<DtoProduct> addProduct(@Valid @RequestBody DtoProductIU dtoProduct) {
        return ApiResponse.success(productService.createProduct(dtoProduct), ResponseMessage.PRODUCT_ADDED);
    }

    @PutMapping("/{id}")
    @Override
    public ApiResponse<DtoProduct> updateProduct(@PathVariable Long id,
                                                 @Valid @RequestBody DtoProductIU dtoProduct) {
        return ApiResponse.success(productService.updateProduct(id, dtoProduct), ResponseMessage.PRODUCT_UPDATED);
    }

    @DeleteMapping("/{id}")
    @Override
    public ApiResponse<Boolean> deleteProduct(@PathVariable Long id) {
        return ApiResponse.success(productService.deleteProduct(id), ResponseMessage.PRODUCT_DELETED);
    }
}
