package com.murathnakts.controller;

import com.murathnakts.dto.DtoOrder;
import com.murathnakts.dto.DtoOrderIU;
import com.murathnakts.dto.DtoOrderItem;
import com.murathnakts.handler.ApiResponse;

import java.util.List;

public interface IOrderController {
    ApiResponse<List<DtoOrder>> getAllOrders();
    ApiResponse<DtoOrder> getOrderById(Long id);
    ApiResponse<DtoOrder> createOrder(DtoOrderIU dtoOrderIU);
    ApiResponse<DtoOrder> cancelOrder(Long id);
    ApiResponse<List<DtoOrderItem>> getAllIncomingOrders();
}
