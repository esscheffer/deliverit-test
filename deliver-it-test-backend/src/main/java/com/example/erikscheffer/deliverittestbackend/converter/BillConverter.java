package com.example.erikscheffer.deliverittestbackend.converter;

import com.example.erikscheffer.deliverittestbackend.model.Bill;
import com.example.erikscheffer.deliverittestbackend.model.api.BillApiCreate;

public class BillConverter {
    public static Bill billApiCreateToBill(BillApiCreate billApiCreate) {
        return Bill.builder()
                .name(billApiCreate.getName())
                .originalValue(billApiCreate.getOriginalValue())
                .dueDate(billApiCreate.getDueDate())
                .paymentDate(billApiCreate.getPaymentDate())
                .build();
    }
}
