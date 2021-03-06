package com.shymoniak.expenses.service;

import com.shymoniak.expenses.domain.ExpensesDTO;

import java.util.List;
import java.util.Map;

public interface ExpensesService {
    ExpensesDTO addExpenses(ExpensesDTO expenses);

    Map<String, List<ExpensesDTO>> getAllExpensesGroupedByDateSorted();

    List<ExpensesDTO> deleteExpensesAtDay(String day);

    List<ExpensesDTO> getAllExpenses();
}
