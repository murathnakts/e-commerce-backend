package com.murathnakts.service.impl;

import com.murathnakts.dto.DtoOrder;
import com.murathnakts.dto.DtoOrderIU;
import com.murathnakts.dto.DtoOrderItem;
import com.murathnakts.entity.Cart;
import com.murathnakts.entity.Order;
import com.murathnakts.entity.OrderItem;
import com.murathnakts.enums.OrderStatus;
import com.murathnakts.handler.BaseException;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.mapper.Mapper;
import com.murathnakts.repository.OrderItemRepository;
import com.murathnakts.repository.OrderRepository;
import com.murathnakts.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    private final ICartService cartService;
    private final IProductService productService;
    private final ICustomerService customerService;
    private final IJwtService jwtService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(ICartService cartService,
                            IProductService productService,
                            ICustomerService customerService,
                            IJwtService jwtService,
                            OrderItemRepository orderItemRepository,
                            OrderRepository orderRepository) {
        this.cartService = cartService;
        this.productService = productService;
        this.customerService = customerService;
        this.jwtService = jwtService;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Order> findByCustomerId(Long id) {
        return orderRepository.findByCustomerId(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<OrderItem> findBySellerId(Long id) {
        return orderItemRepository.findBySellerId(id)
                .orElseThrow(() -> new BaseException(ResponseMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<DtoOrder> getAllOrders() {
        return Mapper.toDtoOrder(findByCustomerId(jwtService.getCurrentUserId()));
    }

    @Override
    public DtoOrder getOrderById(Long id) {
        return Mapper.toDtoOrder(findById(id));
    }

    @Transactional
    @Override
    public DtoOrder createOrder(DtoOrderIU dtoOrderIU) {
        long userId = jwtService.getCurrentUserId();
        Cart cart = cartService.findByCustomerId(userId);
        if (cart.getCartItems().isEmpty()) {
            throw new BaseException(ResponseMessage.CART_IS_EMPTY);
        }

        Order order = new Order();
        cart.getCartItems().forEach(cartItem -> {
            cartService.validateStockForOrder(cartItem.getProduct(), cartItem.getCount());
            productService.decreaseStock(cartItem.getProduct(), cartItem.getCount());
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProduct().getId());
            orderItem.setProductName(cartItem.getProduct().getName());
            orderItem.setProductAmount(cartItem.getProduct().getAmount());
            orderItem.setCount(cartItem.getCount());
            orderItem.setItemAmount(cartItem.getItemAmount());
            orderItem.setStatus(OrderStatus.PROCESSING);
            orderItem.setSeller(cartItem.getProduct().getSeller());
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        });

        order.setCustomer(customerService.findById(userId));
        order.setTotalAmount(cart.getTotalAmount());
        order.setShippingAddress(dtoOrderIU.getShippingAddress());
        order.setPaymentMethod(dtoOrderIU.getPaymentMethod());

        //TODO Payment service impl
        order.setTransactionId("TXN_" + System.currentTimeMillis());
        orderRepository.save(order);
        cartService.clearCart();
        return Mapper.toDtoOrder(order);
    }

    @Transactional
    @Override
    public DtoOrder cancelOrder(Long id) {
        Order order = findById(id);

        //TODO control order item status

        order.getOrderItems().forEach(item -> {
            item.setStatus(OrderStatus.CANCELLED);
            productService.increaseStock(productService.findById(item.getProductId()), item.getCount());
        });

        return Mapper.toDtoOrder(orderRepository.save(order));
    }

    @Override
    public List<DtoOrderItem> getAllIncomingOrders() {
        return Mapper.toDtoOrderItem(findBySellerId(jwtService.getCurrentUserId()));
    }
}
