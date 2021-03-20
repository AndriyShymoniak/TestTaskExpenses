package com.shymoniak.expenses.service.impl;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.repository.ExpensesRepository;
import com.shymoniak.expenses.service.ExpensesService;
import com.shymoniak.expenses.service.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Map<String, List<ExpensesDTO>> result = expensesDtoList.stream()
                .collect(Collectors.groupingBy(e -> e.getDate()));
        return new TreeMap(result);
    }


    @Override
    public void deleteExpensesAtDay(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(day));
            Date from = sdf.parse(sdf.format(c.getTime()));
            c.add(Calendar.DATE, 1);
            Date to = sdf.parse(sdf.format(c.getTime()));
            repository.deleteAllByDateBetween(from.toInstant(), to.toInstant());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
