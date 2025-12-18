package com.murathnakts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrder extends DtoBase{
    private List<DtoOrderItem> orderItems;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    private String transactionId;
}
