package com.murathnakts.dto;

import com.murathnakts.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrderItem {
    private Long productId;
    private String productName;
    private BigDecimal productAmount;
    private Integer count;
    private BigDecimal itemAmount;
    private OrderStatus status;
    private String sellerEmail;
}
