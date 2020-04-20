package com.example.erikscheffer.deliverittestbackend.model;

import com.example.erikscheffer.deliverittestbackend.model.enumeration.CalculationRule;
import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@ToString
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private BigDecimal originalValue;
    private BigDecimal adjustedValue;
    private LocalDate paymentDate;
    private LocalDate dueDate;
    private Integer dueDays;
    @Enumerated(EnumType.STRING) private CalculationRule rule;
}
