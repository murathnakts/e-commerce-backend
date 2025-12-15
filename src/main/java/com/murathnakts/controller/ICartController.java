package com.murathnakts.controller;

import com.murathnakts.dto.DtoCart;
import com.murathnakts.dto.DtoCartIU;
import com.murathnakts.handler.ApiResponse;

public interface ICartController {
    ApiResponse<DtoCart> getCart();
    ApiResponse<DtoCart> addCart(DtoCartIU dtoCartIU);
    ApiResponse<DtoCart> increaseCount(Long productId);
    ApiResponse<DtoCart> decreaseCount(Long productId);
    ApiResponse<Boolean> removeCart(Long productId);
    ApiResponse<Boolean> clearCart();
}
