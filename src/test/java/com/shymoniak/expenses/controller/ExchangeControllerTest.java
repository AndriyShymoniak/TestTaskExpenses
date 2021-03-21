package com.shymoniak.expenses.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showAllExpenses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/total?base=UAH")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/total?base=Uah")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        mockMvc.perform(MockMvcRequestBuilders.get("/total?base=AAA")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        mockMvc.perform(MockMvcRequestBuilders.get("/total?base=")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}