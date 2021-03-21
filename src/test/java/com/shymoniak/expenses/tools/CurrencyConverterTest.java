package com.shymoniak.expenses.tools;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CurrencyConverterTest {

    @Test
    public void getExchangeValues() throws IOException {
        CurrencyConverter converter = new CurrencyConverter();
        assertTrue(converter.getExchangeValues().size() > 0);
    }
}