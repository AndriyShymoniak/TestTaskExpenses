package com.shymoniak.expenses.controller;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.exception.ApiRequestException;
import com.shymoniak.expenses.service.ExpensesService;
import com.shymoniak.expenses.tools.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService service;

    @Autowired
    private Validator validator;

    @GetMapping({"", "/"})
    ResponseEntity<Map<String, List<ExpensesDTO>>> showAllExpenses() {
        return new ResponseEntity<>(service.getAllExpensesGroupedByDateSorted(),
                HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<ExpensesDTO> addExpenses(@RequestBody ExpensesDTO expensesDTO) {
        if (validator.isValidExpensesDto(expensesDTO)) {
            service.addExpenses(expensesDTO);
            return new ResponseEntity<>(expensesDTO, HttpStatus.CREATED);
        } else {
            throw new ApiRequestException("Invalid expenses data.");
        }
    }

    @DeleteMapping
    ResponseEntity<List<ExpensesDTO>> deleteExpenses(@RequestParam("date") String sDate) {
        if (validator.isValidDate(sDate)) {
            return new ResponseEntity<>(service.deleteExpensesAtDay(sDate),
                    HttpStatus.OK);
        } else {
            throw new ApiRequestException("Wrong date.");
        }
    }
}
