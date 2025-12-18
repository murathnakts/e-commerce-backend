package com.murathnakts.controller.impl;

import com.murathnakts.controller.IOrderController;
import com.murathnakts.dto.DtoOrder;
import com.murathnakts.dto.DtoOrderIU;
import com.murathnakts.dto.DtoOrderItem;
import com.murathnakts.handler.ApiResponse;
import com.murathnakts.handler.ResponseMessage;
import com.murathnakts.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderControllerImpl implements IOrderController {

    private final IOrderService orderService;

    public OrderControllerImpl(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @Override
    public ApiResponse<List<DtoOrder>> getAllOrders() {
        return ApiResponse.success(orderService.getAllOrders(), ResponseMessage.SUCCESS);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER') and @OrderSecurityService.isOrderOwner(#id)")
    @Override
    public ApiResponse<DtoOrder> getOrderById(@PathVariable Long id) {
        return ApiResponse.success(orderService.getOrderById(id), ResponseMessage.SUCCESS);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @Override
    public ApiResponse<DtoOrder> createOrder(@Valid @RequestBody DtoOrderIU dtoOrderIU) {
        return ApiResponse.success(orderService.createOrder(dtoOrderIU), ResponseMessage.ORDER_CREATED);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER') and @OrderSecurityService.isOrderOwner(#id)")
    @Override
    public ApiResponse<DtoOrder> cancelOrder(@PathVariable Long id) {
        return ApiResponse.success(orderService.cancelOrder(id), ResponseMessage.SUCCESS);
    }

    @GetMapping("/seller")
    @PreAuthorize("hasAnyRole('SELLER')")
    @Override
    public ApiResponse<List<DtoOrderItem>> getAllIncomingOrders() {
        return ApiResponse.success(orderService.getAllIncomingOrders(), ResponseMessage.SUCCESS);
    }
}
