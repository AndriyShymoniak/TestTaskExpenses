package com.shymoniak.expenses.controller;

import com.shymoniak.expenses.domain.ExpensesDTO;
import com.shymoniak.expenses.service.ExpensesService;
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

    @GetMapping({"", "/"})
    ResponseEntity<Map<String, List<ExpensesDTO>>> showAllExpenses() {
        return new ResponseEntity<>(service.getAllExpensesGroupedByDateSorted(),
                HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Void> addExpenses(@RequestBody ExpensesDTO expenses) {
        service.addExpenses(expenses);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping()
    ResponseEntity<Void> deleteExpenses(@RequestParam("date") String sDate) {
        service.deleteExpensesAtDay(sDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
