package com.murathnakts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DtoOrderIU {
    @NotBlank
    private String shippingAddress;

    @NotBlank
    private String paymentMethod;
}
