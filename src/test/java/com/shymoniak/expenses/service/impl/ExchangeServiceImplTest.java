package com.shymoniak.expenses.service.impl;

import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class ExchangeServiceImplTest {

    @Test
    public void getExchangeValues() throws IOException {
//        ExchangeServiceImpl expensesService = new ExchangeServiceImpl();
//        HashMap<String, Object> hashMap = expensesService.getExchangerResponse();
//        System.out.println(hashMap);
//        List<String> list =
//                Arrays.stream(hashMap.get("rates").toString()
//                        .replaceAll("\\{|\\}|\\s", "")
//                        .split(","))
//                        .collect(Collectors.toList());
//
//        Map<String, Double> exchangeRate = new TreeMap<>(String::compareTo);
//        String[] splited;
//        for (int i = 0; i < list.size(); i++) {
//            splited = list.get(i).split("=");
//            exchangeRate.put(splited[0], Double.valueOf(splited[1]));
//        }
//        String base = hashMap.get("base").toString();
//        System.out.println(base);
//        System.out.println(exchangeRate);
//        System.out.println();
    }

//    @Test
//    public void exchange() throws IOException{
//        ExchangeServiceImpl expensesService = new ExchangeServiceImpl();
//        Map<String, Double> vals = expensesService.getExchangeValues();
//        double val = expensesService.exchange("USD", "UAH");
//        System.out.println( val);
//        System.out.println();
//    }
}