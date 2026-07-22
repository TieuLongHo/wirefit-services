package com.acme.orders.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;

/** JSON returned by GET /orders/{id}. */
public class OrderResponse {
    @JsonProperty(value = "order_id", required = true)
    @Nonnull
    public String orderId;

    @JsonProperty(value = "status", required = true)
    @Nonnull
    public String status;

    @JsonProperty(value = "total_cents", required = true)
    public long totalCents;

    @JsonProperty(value = "customer_email", required = true)
    @Nonnull
    public String customerEmail;
}
