package com.shymoniak.expenses.service.impl;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.service.ExchangeService;
import com.shymoniak.expenses.tools.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private CurrencyConverter converter;

    @Override
    public double getTotalExpensesInBase(List<ExpensesDTO> expensesList,
                                         String base) {
        Map<String, Double> sumInCurrency = new TreeMap<>(String::compareTo);
        for (ExpensesDTO ex : expensesList) {
            if (sumInCurrency.containsKey(ex.getCurrency())) {
                sumInCurrency.put(ex.getCurrency(),
                        sumInCurrency.get(ex.getCurrency()) + ex.getAmount());
            } else {
                sumInCurrency.put(ex.getCurrency(), ex.getAmount());
            }
        }
        sumInCurrency.replaceAll((k, v) -> v = exchange(k, base, v));
        return sumInCurrency.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public double exchange(String from, String to, double amount) {
        Map<String, Double> currencyValues = converter.getExchangeValues();
        return amount * currencyValues.get(to) / currencyValues.get(from);
    }
}
