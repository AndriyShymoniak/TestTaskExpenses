package com.shymoniak.expenses.tools;

import com.shymoniak.expenses.constant.ApiConstants;
import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.entity.Expenses;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectMapperUtilsTest {

    @Autowired
    private ObjectMapperUtils modelMapper;

    private List<Expenses> expensesList;
    private List<ExpensesDTO> expensesDTOList;

    @Before
    public void setInitialValues() throws ParseException {
        expensesList = Stream.of(
            new Expenses(1L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-02-28").toInstant(),2300.0,"UAH","Shoes"),
            new Expenses(2L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-02-28").toInstant(),400.0,"UAH","T-shirt"),
            new Expenses(3L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-02-28").toInstant(),10.0,"USD","Cinema ticket"),
            new Expenses(4L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-03-10").toInstant(),111.0,"USD","Phone repair"),
            new Expenses(5L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-03-11").toInstant(),50.0,"UAH","Jogurt"),
            new Expenses(6L, new SimpleDateFormat(ApiConstants.DATE_FORMAT).parse("2021-03-11").toInstant(),30.0,"UAH","Ice-cream")
        ).collect(Collectors.toList());
        expensesDTOList = Stream.of(
                new ExpensesDTO(1L, "2021-02-28",2300.0,"UAH","Shoes"),
                new ExpensesDTO(2L, "2021-02-28",400.0,"UAH","T-shirt"),
                new ExpensesDTO(3L, "2021-02-28",10.0,"USD","Cinema ticket"),
                new ExpensesDTO(4L, "2021-03-10",111.0,"USD","Phone repair"),
                new ExpensesDTO(5L, "2021-03-11",50.0,"UAH","Jogurt"),
                new ExpensesDTO(6L, "2021-03-11",30.0,"UAH","Ice-cream")
        ).collect(Collectors.toList());
    }


    @Test
    public void convertToDto() {
        ExpensesDTO expensesDTO;
        for (int i = 0; i < expensesList.size(); i++) {
            expensesDTO = modelMapper.convertToDto(expensesList.get(i));
            assertEquals(expensesDTO, expensesDTOList.get(i));
        }
    }

    @Test
    public void convertToEntity() {
        Expenses expenses;
        for (int i = 0; i < expensesDTOList.size(); i++) {
            expenses = modelMapper.convertToEntity(expensesDTOList.get(i));
            expenses.setDate(expenses.getDate().minusSeconds(7200));
            assertEquals(expenses, expensesList.get(i));
        }
    }
}