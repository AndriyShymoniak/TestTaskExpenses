package com.shymoniak.expenses.tools;

import com.shymoniak.expenses.domain.ExpensesDTO;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class ValidatorTest {

    private Validator validator = new Validator();

    @Test
    public void isValidDate() {
        assertTrue(validator.isValidDate("1900-12-31"));
        assertTrue(validator.isValidDate("2021-03-21"));
        assertTrue(validator.isValidDate("2999-01-01"));
        assertFalse(validator.isValidDate("1899-03-10"));
        assertFalse(validator.isValidDate("3000-01-01"));
        assertFalse(validator.isValidDate("01-01-2012"));
        assertFalse(validator.isValidDate("dsvavawva"));
        assertFalse(validator.isValidDate(""));
        assertFalse(validator.isValidDate(null));
    }

    @Test
    public void isValidBase() {

        String[] correctBase = {"UAH", "USD", "EUR", "PLN"};
        String[] incorrectBase = {"UAh", "usd", "AAA", "", null, "5442"};
        for(String str: correctBase){
            assertTrue(isValidBaseLogic(str));
        }
        for(String str: incorrectBase){
            assertFalse(isValidBaseLogic(str));
        }
    }

    private boolean isValidBaseLogic(String base){
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Set<String> keys = (currencyConverter.getExchangeValues().keySet());
        return base != null
                && base.matches("[A-Z]{3}")
                && keys.contains(base);
    }

    @Test
    public void isValidExpensesDto() {
        assertTrue(isValidExpansesLogic(new ExpensesDTO(1L, "2021-02-28", 1000, "UAH", "Shoes")));
        assertFalse(isValidExpansesLogic(new ExpensesDTO(2L, "2021-13-50", 1000, "UAH", "Shoes")));
        assertFalse(isValidExpansesLogic(new ExpensesDTO(3L, "02-05-2015", 1000, "UAH", "Shoes")));
        assertFalse(isValidExpansesLogic(new ExpensesDTO(4L, "2021-02-28", 0, "UAH", "Shoes")));
        assertFalse(isValidExpansesLogic(new ExpensesDTO(5L, "2021-02-28", 1000, "uah", "Shoes")));
        assertFalse(isValidExpansesLogic(new ExpensesDTO(6L, "2021-02-28", 1000, "UAH", "")));
    }

    private boolean isValidExpansesLogic(ExpensesDTO dto){
        return validator.isValidDate(dto.getDate())
                && dto.getAmount() > 0
                && isValidBaseLogic(dto.getCurrency())
                && dto.getProduct() != null
                && dto.getProduct().length() != 0;
    }
}