package com.murathnakts.service;

import com.murathnakts.dto.DtoOrder;
import com.murathnakts.dto.DtoOrderIU;
import com.murathnakts.dto.DtoOrderItem;
import com.murathnakts.entity.Order;
import com.murathnakts.entity.OrderItem;

import java.util.List;

public interface IOrderService {
    Order findById(Long id);
    List<Order> findByCustomerId(Long id);
    List<OrderItem> findBySellerId(Long id);
    List<DtoOrder> getAllOrders();
    DtoOrder getOrderById(Long id);
    DtoOrder createOrder(DtoOrderIU dtoOrderIU);
    DtoOrder cancelOrder(Long id);
    List<DtoOrderItem> getAllIncomingOrders();
}
