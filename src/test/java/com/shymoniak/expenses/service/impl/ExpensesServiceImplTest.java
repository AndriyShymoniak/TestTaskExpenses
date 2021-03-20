package com.shymoniak.expenses.service.impl;

import com.shymoniak.expenses.constant.ApiConstants;
import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.entity.Expenses;
import com.shymoniak.expenses.repository.ExpensesRepository;
import com.shymoniak.expenses.service.utils.ObjectMapperUtils;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;


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
        Mockito.when(repository.findAll()).thenReturn(Stream.of(
                new Expenses(1L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-02-28").toInstant(),2300.0,"UAH","Shoes"),
                new Expenses(2L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-02-28").toInstant(),400.0,"UAH","T-shirt"),
                new Expenses(3L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-02-28").toInstant(),10.0,"USD","Cinema ticket"),
                new Expenses(4L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-03-10").toInstant(),111.0,"USD","Phone repair"),
                new Expenses(5L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-03-11").toInstant(),50.0,"UAH","Jogurt"),
                new Expenses(6L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-03-11").toInstant(),30.0,"UAH","Ice-cream")
        ).collect(Collectors.toList()));
    }

    @Test
    public void testGetAllExpenses() {
        Map<String, List<ExpensesDTO>> listMap = service.getAllExpensesGroupedByDateSorted();
        assertEquals(3, listMap.get("2021-02-28").size());
    }

    @Test
    public void addExpenses() {
    }

    @Test
    public void testDeleteAtDay() throws ParseException{
        Date date = new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-03-11");
        Instant reqInstant = date.toInstant();
        Map<String, List<ExpensesDTO>> listMap = service.getAllExpensesGroupedByDateSorted();
        assertEquals(2, listMap.get("2021-03-11").size());
//        service.deleteExpensesAtDay(reqInstant);
        listMap = service.getAllExpensesGroupedByDateSorted();
        System.out.println(listMap);
        assertEquals(0, listMap.get("2021-03-11").size());
    }
}