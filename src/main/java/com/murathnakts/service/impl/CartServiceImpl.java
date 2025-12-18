package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoCart;
import com.murathnakts.dto.DtoCartIU;
import com.murathnakts.entity.*;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.mapper.Mapper;
import com.murathnakts.repository.CartRepository;
import com.murathnakts.service.ICartService;
import com.murathnakts.service.IJwtService;
import com.murathnakts.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements ICartService {

    private final IProductService productService;
    private final IJwtService jwtService;
    private final CartRepository cartRepository;

    public CartServiceImpl(IProductService productService,
                           IJwtService jwtService,
                           CartRepository cartRepository) {
        this.productService = productService;
        this.jwtService = jwtService;
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart findByCustomerId(Long id) {
        return cartRepository.findByCustomerId(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.CART_NOT_FOUND));
    }

    @Override
    public CartItem findItemInCart(Cart cart, Product product) {
        return cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void validateStock(Product product, CartItem cartItem, int count) {
        int currentCount = (cartItem != null) ? cartItem.getCount() : 0;
        int finalCount = currentCount + count;

        if (finalCount > product.getStock()) {
            throw new BaseException(ResponseMessage.STOCK_NOT_ENOUGH);
        }
    }

    @Override
    public void validateStockForOrder(Product product, int count) {
        if (count > product.getStock()) {
            throw new BaseException(ResponseMessage.STOCK_NOT_ENOUGH);
        }
    }

    @Override
    public void updateTotalAmount(Cart cart) {
        BigDecimal total = cart.getCartItems()
                .stream()
                .map(CartItem::getItemAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        cart.setTotalAmount(total);
    }

    @Override
    public DtoCart getCart() {
        Cart cart = findByCustomerId(jwtService.getCurrentUserId());
        return Mapper.toDtoCart(cart);
    }

    @Override
    public void addOrIncrease(Cart cart, Product product, int count) {
        CartItem existingItem = findItemInCart(cart, product);

        if (existingItem != null) {
            int newCount = existingItem.getCount() + count;
            existingItem.setCount(newCount);
            existingItem.setItemAmount(product.getAmount().multiply(BigDecimal.valueOf(newCount)));
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCount(count);
            cartItem.setItemAmount(product.getAmount().multiply(BigDecimal.valueOf(count)));
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }
    }

    @Override
    @Transactional
    public DtoCart addCart(DtoCartIU dtoCartIU) {
        Cart cart = findByCustomerId(jwtService.getCurrentUserId());

        Product product = productService.findById(dtoCartIU.getProductId());
        CartItem existingItem = findItemInCart(cart, product);

        validateStock(product, existingItem, dtoCartIU.getCount());
        addOrIncrease(cart, product, dtoCartIU.getCount());

        updateTotalAmount(cart);
        return Mapper.toDtoCart(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public DtoCart increaseCount(Long productId) {
        Cart cart = findByCustomerId(jwtService.getCurrentUserId());

        Product product = productService.findById(productId);
        CartItem cartItem = findItemInCart(cart, product);

        if (cartItem == null) {
            throw new BaseException(ResponseMessage.PRODUCT_NOT_FOUND);
        }

        validateStock(product, cartItem, 1);
        addOrIncrease(cart, product, 1);

        updateTotalAmount(cart);
        return Mapper.toDtoCart(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public DtoCart decreaseCount(Long productId) {
        Cart cart = findByCustomerId(jwtService.getCurrentUserId());
        Product product = productService.findById(productId);
        CartItem cartItem = findItemInCart(cart, product);

        if (cartItem == null) {
            throw new BaseException(ResponseMessage.PRODUCT_NOT_FOUND);
        }

        if (cartItem.getCount() <= 1) {
            cart.getCartItems().remove(cartItem);
        } else {
            int newCount = cartItem.getCount() - 1;
            cartItem.setCount(newCount);
            cartItem.setItemAmount(product.getAmount().multiply(BigDecimal.valueOf(newCount)));
        }

        updateTotalAmount(cart);
        return Mapper.toDtoCart(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public Boolean removeCart(Long productId) {
        Cart cart = findByCustomerId(jwtService.getCurrentUserId());
        Product product = productService.findById(productId);
        CartItem cartItem = findItemInCart(cart, product);

        if (cartItem == null) {
            throw new BaseException(ResponseMessage.PRODUCT_NOT_FOUND);
        }

        cart.getCartItems().remove(cartItem);
        updateTotalAmount(cart);
        cartRepository.save(cart);
        return true;
    }

    @Override
    @Transactional
    public Boolean clearCart() {
        Cart cart = findByCustomerId(jwtService.getCurrentUserId());
        cart.getCartItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cartRepository.save(cart);
        return true;
    }
}
