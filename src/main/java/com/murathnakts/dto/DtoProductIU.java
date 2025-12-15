package com.murathnakts.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DtoProductIU {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Min(value = 0)
    private Integer stock;
    @NotBlank
    private String category;
    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal amount;
}
