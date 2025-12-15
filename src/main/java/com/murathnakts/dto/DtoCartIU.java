package com.murathnakts.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DtoCartIU {
    @NotNull
    private Long productId;
    @NotNull
    @Min(1)
    private Integer count;
}
