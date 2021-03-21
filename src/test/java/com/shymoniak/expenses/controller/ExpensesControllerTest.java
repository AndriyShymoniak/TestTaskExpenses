package com.shymoniak.expenses.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExpensesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showAllExpenses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/expenses/")
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/expenses")
                .contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void addExpenses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/expenses")
                .contentType("application/json")
                .content("{\"date\":\"2021-02-444\",\"amount\":\"40\"," +
                        "\"currency\":\"UAH\",\"product\":\"Car service\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(
                "/expenses")
                .contentType("application/json")
                .content("{\"date\":\"2021-02-01\",\"amount\":\"40\"," +
                        "\"currency\":\"UAH\",\"product\":\"Car service\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        Assert.assertTrue(mvcResult.getResponse().getContentAsString().length() != 0);
    }

    @Test
    public void deleteExpenses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/expenses")
                .contentType("application/json")
                .param("date", "02-00-2020"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
        String date = "2021-02-01";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .delete("/expenses")
                .contentType("application/json")
                .param("date", date))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String res = mvcResult.getResponse().getContentAsString();
        System.out.println(res);
    }
}