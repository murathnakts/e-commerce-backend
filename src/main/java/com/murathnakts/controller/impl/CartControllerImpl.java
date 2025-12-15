package com.murathnakts.controller.impl;

import com.murathnakts.controller.ICartController;
import com.murathnakts.dto.DtoCart;
import com.murathnakts.dto.DtoCartIU;
import com.murathnakts.handler.ApiResponse;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartControllerImpl implements ICartController {

    private final ICartService cartService;

    public CartControllerImpl(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Override
    public ApiResponse<DtoCart> getCart() {
        return ApiResponse.success(cartService.getCart(), ResponseMessage.SUCCESS);
    }

    @PostMapping
    @Override
    public ApiResponse<DtoCart> addCart(@Valid @RequestBody DtoCartIU dtoCartIU) {
        return ApiResponse.success(cartService.addCart(dtoCartIU), ResponseMessage.SUCCESS);
    }

    @PatchMapping("/{productId}/increase")
    @Override
    public ApiResponse<DtoCart> increaseCount(@PathVariable Long productId) {
        return ApiResponse.success(cartService.increaseCount(productId), ResponseMessage.SUCCESS);
    }

    @PatchMapping("/{productId}/decrease")
    @Override
    public ApiResponse<DtoCart> decreaseCount(@PathVariable Long productId) {
        return ApiResponse.success(cartService.decreaseCount(productId), ResponseMessage.SUCCESS);
    }

    @DeleteMapping("/{productId}")
    @Override
    public ApiResponse<Boolean> removeCart(@PathVariable Long productId) {
        return ApiResponse.success(cartService.removeCart(productId), ResponseMessage.SUCCESS);
    }

    @DeleteMapping("/clear")
    @Override
    public ApiResponse<Boolean> clearCart() {
        return ApiResponse.success(cartService.clearCart(), ResponseMessage.SUCCESS);

    }
}
