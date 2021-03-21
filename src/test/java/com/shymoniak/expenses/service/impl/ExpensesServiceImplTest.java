package com.shymoniak.expenses.service.impl;

import com.shymoniak.expenses.constant.ApiConstants;
import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.entity.Expenses;
import com.shymoniak.expenses.repository.ExpensesRepository;
import com.shymoniak.expenses.tools.ObjectMapperUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.shymoniak.expenses.constant.ApiConstants.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpensesServiceImplTest {

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Autowired
    private ExpensesServiceImpl service;

    @MockBean
    private ExpensesRepository repository;

    @Before
    public void setTestValues() throws ParseException{
        when(repository.findAll()).thenReturn(Stream.of(
                new Expenses(1L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-02-28").toInstant(),2300.0,"UAH","Shoes"),
                new Expenses(2L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-02-28").toInstant(),400.0,"UAH","T-shirt"),
                new Expenses(3L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-02-28").toInstant(),10.0,"USD","Cinema ticket"),
                new Expenses(4L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-03-10").toInstant(),111.0,"USD","Phone repair"),
                new Expenses(5L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-03-11").toInstant(),50.0,"UAH","Jogurt"),
                new Expenses(6L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-03-11").toInstant(),30.0,"UAH","Ice-cream")
        ).collect(Collectors.toList()));
    }

    @Test
    public void getAllExpenses() {
        Map<String, List<ExpensesDTO>> listMap = service.getAllExpensesGroupedByDateSorted();
        assertEquals(3, listMap.get("2021-02-28").size());
    }

    @Test
    public void addExpenses() throws ParseException {
        Expenses expenses = new Expenses(7L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                .parse("2021-03-10").toInstant(),1.5,"USD","Socks");
        when(repository.save(expenses)).thenReturn(expenses);
        ExpensesDTO returnedExpenses =
                service.addExpenses(modelMapper.convertToDto(expenses));
        verify(repository, times(1)).save(Mockito.any(Expenses.class));
        assertEquals(returnedExpenses, modelMapper.convertToDto(expenses));
    }

    @Test
    public void deleteAtDay() throws ParseException {
        String date = "2021-03-11";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        Date from = sdf.parse(sdf.format(c.getTime()));
        c.add(Calendar.DATE, 1);
        Date to = sdf.parse(sdf.format(c.getTime()));
        List<Expenses> list = Stream.of(
                new Expenses(5L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-03-11").toInstant(),50.0,"UAH","Jogurt"),
                new Expenses(6L, new SimpleDateFormat(ApiConstants.DATE_FORMAT)
                        .parse("2021-03-11").toInstant(),30.0,"UAH","Ice-cream")
        ).collect(Collectors.toList());
        when(repository.deleteAllByDateBetween(from.toInstant(), to.toInstant()))
                .thenReturn(list);
        assertEquals(2, service.deleteExpensesAtDay(date).size());
        verify(repository,times(1))
                .deleteAllByDateBetween(from.toInstant(), to.toInstant());
    }
}