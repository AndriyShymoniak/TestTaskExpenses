package com.shymoniak.expenses.service;

import com.shymoniak.expenses.domain.ExpensesDTO;

import java.util.List;

public interface ExchangeService {
    double getTotalExpensesInBase(List<ExpensesDTO> expensesList, String base);

}
