package com.shymoniak.expenses.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyConverterTest {

    @Test
    public void getExchangeValues() {
        CurrencyConverter converter = new CurrencyConverter();
        assertTrue(converter.getExchangeValues().size() > 0);
    }
}