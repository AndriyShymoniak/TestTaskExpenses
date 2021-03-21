package com.shymoniak.expenses.tools;

import java.text.ParseException;
import java.util.Date;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.entity.Expenses;
import com.shymoniak.expenses.exception.ApiRequestException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperUtils {

    @Autowired
    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ObjectMapperUtils() {
    }

    public ExpensesDTO convertToDto(Expenses expenses) {
        ExpensesDTO expensesDTO = modelMapper.map(expenses, ExpensesDTO.class);
        expensesDTO.setDate(Date.from(expenses.getDate()));
        return expensesDTO;
    }

    public Expenses convertToEntity(ExpensesDTO expensesDTO) {
        Expenses expenses = modelMapper.map(expensesDTO, Expenses.class);
        try {
            expenses.setDate(expensesDTO.convertDate());
            return expenses;
        } catch (ParseException ex) {
            throw new ApiRequestException("Unable to parse date:"
                    + expensesDTO.getDate());
        }
    }
}