package com.shymoniak.expenses.controller;

import com.shymoniak.expenses.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("total")
public class ExchangeController {

    @Autowired
    private ExchangeService service;

    @GetMapping({"", "/"})
    ResponseEntity<Double> showAllExpenses() {
        try {
            return new ResponseEntity<>(service.exchange("UAH", "USD", 100),
                    HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
