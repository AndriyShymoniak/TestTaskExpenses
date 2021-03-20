package com.shymoniak.expenses.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_purchased")
    private Instant date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "product", length = 50)
    private String product;
}
