package com.shymoniak.expenses.service;

import com.shymoniak.expenses.domain.ExpensesDTO;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExpensesService{
    void addExpenses(ExpensesDTO expenses);

    Map<String, List<ExpensesDTO>> getAllExpensesGroupedByDateSorted();

    void deleteExpensesAtDay(String day);
}
