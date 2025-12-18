package com.murathnakts.service;

import com.murathnakts.dto.DtoCart;
import com.murathnakts.dto.DtoCartIU;
import com.murathnakts.entity.Cart;
import com.murathnakts.entity.CartItem;
import com.murathnakts.entity.Product;

public interface ICartService {
    Cart findByCustomerId(Long id);
    CartItem findItemInCart(Cart cart, Product product);
    void validateStock(Product product, CartItem cartItem, int count);
    void validateStockForOrder(Product product, int count);
    void updateTotalAmount(Cart cart);
    DtoCart getCart();
    void addOrIncrease(Cart cart, Product product, int count);
    DtoCart addCart(DtoCartIU dtoCartIU);
    DtoCart increaseCount(Long productId);
    DtoCart decreaseCount(Long productId);
    Boolean removeCart(Long productId);
    Boolean clearCart();
}
