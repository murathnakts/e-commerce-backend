package com.murathnakts.security;

public interface IOrderSecurityService {
    Boolean isOrderOwner(Long orderId);
}
