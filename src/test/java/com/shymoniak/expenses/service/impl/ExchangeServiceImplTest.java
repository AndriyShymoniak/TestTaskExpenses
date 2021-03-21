package com.shymoniak.expenses.service.impl;

import com.shymoniak.expenses.domain.ExpensesDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeServiceImplTest {

    @Autowired
    private ExchangeServiceImpl exchangeService;

    @Test
    public void getTotalExpensesInBase() {
        List<ExpensesDTO> expensesList = Stream.of(
                new ExpensesDTO(1L, "2021-02-28",2300.0,"UAH","Shoes"),
                new ExpensesDTO(2L, "2021-02-28",400.0,"UAH","T-shirt"),
                new ExpensesDTO(3L, "2021-02-28",10.0,"USD","Cinema ticket")
        ).collect(Collectors.toList());
        double res = exchangeService.getTotalExpensesInBase(expensesList, "EUR");
        assertTrue(res > 87 && res < 93);
    }

    @Test
    public void exchange() {
        assertTrue(exchangeService.exchange("USD", "UAH", 100) > 2700
        && exchangeService.exchange("USD", "UAH", 100) < 2900);
        assertTrue(exchangeService.exchange("EUR", "USD", 100) > 115
                && exchangeService.exchange("EUR", "USD", 100) < 125);
    }
}