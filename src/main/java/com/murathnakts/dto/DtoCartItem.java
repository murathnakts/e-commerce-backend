package com.murathnakts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCartItem {
    private Long productId;
    private String productName;
    private BigDecimal productAmount;
    private Integer count;
    private BigDecimal itemAmount;
}
