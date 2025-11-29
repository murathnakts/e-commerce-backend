package com.murathnakts.service;

import com.murathnakts.entity.Cart;
import com.murathnakts.entity.Customer;

public interface ICartService {
    Cart createCart(Customer customer);
}
