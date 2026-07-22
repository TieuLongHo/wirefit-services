package com.acme.orders.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/** JSON returned by GET /orders/{id}. */
public class OrderResponse {
    @JsonProperty(value = "order_id", required = true)
    @Nonnull
    public UUID orderId;

    @JsonProperty(value = "status", required = true)
    @Nonnull
    public Status status;

    @JsonProperty(value = "total_cents", required = true)
    public long totalCents;

    @JsonProperty(value = "customer_email", required = true)
    @Nonnull
    public String customerEmail;

    @JsonProperty(value = "items", required = true)
    @Nonnull
    public List<Item> items;

    public enum Status { ACTIVE, BLOCKED, CLOSED }

    public static class Item {
        @Nonnull public String sku;
        public int qty;
        @Nonnull public BigDecimal unitPrice;
    }
}
