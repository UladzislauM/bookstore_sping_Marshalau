package com.company.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusBook {
    IN_PROCESSING,
    ASSEMBLED,
    AWAITING_PAYMENT,
    READY_TO_SHIP,
    SENT,
    PAID_FOR,
    CANCELLED,
    COMPLETELY_CHANGED,
    FAILED,
    DELIVERED,
    REFUND,
    THE_TRANSACTION_IS_COMPLETED,
    _CANCELLED
}
