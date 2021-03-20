package com.shymoniak.expenses.service;

import java.io.IOException;

public interface ExchangeService {
    double exchange(String from, String to, double amount) throws IOException;
}
