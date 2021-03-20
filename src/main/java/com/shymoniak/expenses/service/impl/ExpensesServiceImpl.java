package com.shymoniak.expenses.service.impl;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.entity.Expenses;
import com.shymoniak.expenses.repository.ExpensesRepository;
import com.shymoniak.expenses.service.ExpensesService;
import com.shymoniak.expenses.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Autowired
    private ExpensesRepository repository;

    @Override
    public void addExpenses(ExpensesDTO expenses) {
        repository.save(modelMapper.convertToEntity(expenses));
    }

    @Override
    public Map<String, List<ExpensesDTO>> getAllExpensesGroupedByDateSorted() {
        List<ExpensesDTO> expensesDtoList = repository.findAll()
                .stream()
                .map(e -> modelMapper.convertToDto(e))
                .collect(Collectors.toList());
        expensesDtoList.forEach(e -> System.out.println(e.getDate()));
        Map<String, List<ExpensesDTO>> result = expensesDtoList.stream()
                .collect(Collectors.groupingBy(e -> e.getDate()));
        return new TreeMap(result);
    }


    @Override
    public void deleteExpensesAtDay(Instant day) {
////        repository.deleteAllByDateEquals(day);
////        DateTime dateTimeFrom = new DateTime(day).minusDays(1);
////        DateTime dateTimeTo = new DateTime(day).plusDays(1);
//        OffsetDateTime dateTimeFrom = day.minusDays(1);
//        OffsetDateTime dateTimeTo = day.plusDays(1);
//        repository.deleteAllByDateBetween(dateTimeFrom,dateTimeTo);
    }
}
