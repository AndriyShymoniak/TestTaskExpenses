package com.shymoniak.expenses.service.impl;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.entity.Expenses;
import com.shymoniak.expenses.exception.ApiRequestException;
import com.shymoniak.expenses.repository.ExpensesRepository;
import com.shymoniak.expenses.service.ExpensesService;
import com.shymoniak.expenses.tools.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.shymoniak.expenses.constant.ApiConstants.*;

@Service
public class ExpensesServiceImpl implements ExpensesService {
    @Autowired
    private ObjectMapperUtils modelMapper;

    @Autowired
    private ExpensesRepository repository;

    @Override
    public ExpensesDTO addExpenses(ExpensesDTO expensesDTO) {
        Expenses expenses = modelMapper.convertToEntity(expensesDTO);
        repository.save(expenses);
        return modelMapper.convertToDto(expenses);
    }

    @Override
    public Map<String, List<ExpensesDTO>> getAllExpensesGroupedByDateSorted() {
        List<ExpensesDTO> expensesDtoList = repository.findAll()
                .stream()
                .map(e -> modelMapper.convertToDto(e))
                .collect(Collectors.toList());
        Map<String, List<ExpensesDTO>> result = expensesDtoList.stream()
                .collect(Collectors.groupingBy(ExpensesDTO::getDate));
        return new TreeMap(result);
    }


    @Override
    public List<ExpensesDTO> deleteExpensesAtDay(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c = Calendar.getInstance();
        List<Expenses> expenses;
        try {
            c.setTime(sdf.parse(day));
            Date from = sdf.parse(sdf.format(c.getTime()));
            c.add(Calendar.DATE, 1);
            Date to = sdf.parse(sdf.format(c.getTime()));
            expenses = repository.deleteAllByDateBetween(from.toInstant(),
                    to.toInstant());
            return expenses.stream().map(e -> modelMapper.convertToDto(e))
                    .collect(Collectors.toList());
        } catch (ParseException e) {
            throw new ApiRequestException("Unable to parse date:" + day);
        }
    }

    @Override
    public List<ExpensesDTO> getAllExpenses() {
        return repository.findAll().stream()
                .map(e -> modelMapper.convertToDto(e))
                .collect(Collectors.toList());
    }
}
