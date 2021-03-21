package com.shymoniak.expenses.controller;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.exception.ApiRequestException;
import com.shymoniak.expenses.service.ExchangeService;
import com.shymoniak.expenses.service.ExpensesService;
import com.shymoniak.expenses.tools.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("total")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private ExpensesService expensesService;

    @Autowired
    private Validator validator;

    @GetMapping
    ResponseEntity<Double> showAllExpenses(@RequestParam("base") String base) {
        if (validator.isValidBase(base)){
            List<ExpensesDTO> expenses = expensesService.getAllExpenses();
            return new ResponseEntity<>(
                    exchangeService.getTotalExpensesInBase(expenses, base),
                    HttpStatus.OK);
        } else {
         throw new ApiRequestException("Invalid base format.");
        }
    }
}
