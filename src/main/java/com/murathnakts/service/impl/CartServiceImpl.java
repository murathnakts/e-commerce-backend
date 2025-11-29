package com.murathnakts.service.impl;

import com.murathnakts.entity.Cart;
import com.murathnakts.entity.Customer;
import com.murathnakts.service.ICartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService {

    @Override
    public Cart createCart(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        return cart;
    }
}
