package com.murathnakts.security.impl;

import com.murathnakts.entity.Order;
import com.murathnakts.security.IOrderSecurityService;
import com.murathnakts.service.IJwtService;
import com.murathnakts.service.IOrderService;
import org.springframework.stereotype.Service;

@Service("OrderSecurityService")
public class OrderSecurityServiceImpl implements IOrderSecurityService {

    private final IOrderService orderService;
    private final IJwtService jwtService;

    public OrderSecurityServiceImpl(IOrderService orderService, IJwtService jwtService) {
        this.orderService = orderService;
        this.jwtService = jwtService;
    }

    @Override
    public Boolean isOrderOwner(Long orderId) {
        Order order = orderService.findById(orderId);
        return order.getCustomer().getId().equals(jwtService.getCurrentUserId());
    }
}
