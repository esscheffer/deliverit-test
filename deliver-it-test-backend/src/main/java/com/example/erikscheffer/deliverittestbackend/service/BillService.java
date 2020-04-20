package com.example.erikscheffer.deliverittestbackend.service;

import com.example.erikscheffer.deliverittestbackend.converter.BillConverter;
import com.example.erikscheffer.deliverittestbackend.model.Bill;
import com.example.erikscheffer.deliverittestbackend.model.api.BillApiCreate;
import com.example.erikscheffer.deliverittestbackend.model.enumeration.CalculationRule;
import com.example.erikscheffer.deliverittestbackend.repository.BillRepository;
import com.example.erikscheffer.deliverittestbackend.util.BigDecimalUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    public Bill createBill(BillApiCreate billApiCreate) {
        Bill newBill = BillConverter.billApiCreateToBill(billApiCreate);

        newBill.setDueDays(Math.toIntExact(ChronoUnit.DAYS.between(newBill.getDueDate(), newBill.getPaymentDate())));
        newBill.setRule(CalculationRule.getByDaysCount(newBill.getDueDays()));
        newBill.setAdjustedValue(getAdjustedValue(newBill.getOriginalValue(), newBill.getRule(), newBill.getDueDays()));

        newBill = billRepository.save(newBill);

        return newBill;
    }

    private BigDecimal getAdjustedValue(BigDecimal originalValue, CalculationRule rule, Integer dueDays) {
        BigDecimal totalIncreasePercentage = BigDecimal.ZERO;

        switch (rule) {
            case UP_TO_3_DAYS:
                totalIncreasePercentage = new BigDecimal(2)
                        .add(new BigDecimal("0.1").multiply(new BigDecimal(dueDays)));
                break;
            case OVER_3_DAYS:
                totalIncreasePercentage = new BigDecimal(3)
                        .add(new BigDecimal("0.2").multiply(new BigDecimal(dueDays)));
                break;
            case OVER_5_DAYS:
                totalIncreasePercentage = new BigDecimal(5)
                        .add(new BigDecimal("0.3").multiply(new BigDecimal(dueDays)));
                break;
        }

        return originalValue.add(BigDecimalUtils.percentage(originalValue, totalIncreasePercentage));
    }
}
