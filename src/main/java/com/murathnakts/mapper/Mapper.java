package com.murathnakts.mapper;

import com.murathnakts.dto.DtoCart;
import com.murathnakts.dto.DtoCartItem;
import com.murathnakts.dto.DtoProduct;
import com.murathnakts.dto.DtoUser;
import com.murathnakts.entity.Cart;
import com.murathnakts.entity.CartItem;
import com.murathnakts.entity.Product;
import com.murathnakts.entity.User;

import java.util.Collections;
import java.util.List;

public class Mapper {

    public static DtoUser toDtoUser (User user) {
        if (user == null) return null;
        DtoUser dtoUser = new DtoUser();
        dtoUser.setId(user.getId());
        dtoUser.setCreateTime(user.getCreateTime());
        dtoUser.setEmail(user.getEmail());
        dtoUser.setRole(user.getRole());
        return dtoUser;
    }

    public static List<DtoUser> toDtoUser (List<User> users) {
        if (users == null || users.isEmpty()) return Collections.emptyList();
        return users.stream().map(Mapper::toDtoUser).toList();
    }

    public static DtoProduct toDtoProduct (Product product) {
        if (product == null) return null;
        DtoProduct dtoProduct = new DtoProduct();
        dtoProduct.setId(product.getId());
        dtoProduct.setCreateTime(product.getCreateTime());
        dtoProduct.setName(product.getName());
        dtoProduct.setDescription(product.getDescription());
        dtoProduct.setStock(product.getStock());
        dtoProduct.setCategory(product.getCategory());
        dtoProduct.setAmount(product.getAmount());
        return dtoProduct;
    }

    public static List<DtoProduct> toDtoProduct (List<Product> products) {
        if (products == null || products.isEmpty()) return Collections.emptyList();
        return products.stream().map(Mapper::toDtoProduct).toList();
    }

    public static DtoCartItem toDtoCartItem (CartItem cartItem) {
        if (cartItem == null) return null;
        DtoCartItem dtoCartItem = new DtoCartItem();
        dtoCartItem.setCount(cartItem.getCount());
        dtoCartItem.setProductId(cartItem.getProduct().getId());
        dtoCartItem.setProductName(cartItem.getProduct().getName());
        dtoCartItem.setProductAmount(cartItem.getProduct().getAmount());
        dtoCartItem.setCount(cartItem.getCount());
        dtoCartItem.setItemAmount(cartItem.getItemAmount());
        return dtoCartItem;
    }

    public static List<DtoCartItem> toDtoCartItem (List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) return Collections.emptyList();
        return cartItems.stream().map(Mapper::toDtoCartItem).toList();
    }

    public static DtoCart toDtoCart (Cart cart) {
        if (cart == null) return null;
        DtoCart dtoCart = new DtoCart();
        dtoCart.setCartItems(toDtoCartItem(cart.getCartItems()));
        dtoCart.setTotalAmount(cart.getTotalAmount());
        return dtoCart;
    }
}
