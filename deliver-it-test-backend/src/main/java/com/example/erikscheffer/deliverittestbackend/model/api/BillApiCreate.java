package com.example.erikscheffer.deliverittestbackend.model.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BillApiCreate {
    @NotNull String name;
    @NotNull BigDecimal originalValue;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dueDate;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate paymentDate;
}
