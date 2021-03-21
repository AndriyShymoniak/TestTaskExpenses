package com.shymoniak.expenses.tools;

import com.shymoniak.expenses.domain.ExpensesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Validator {
    @Autowired
    private CurrencyConverter currencyConverter;

    /**
     * @param date date in String
     * @return true if date consists of numbers in YYYY-MM-DD format, has
     * limited years in a range of [1900 – 2999], months in [01-12] and days
     * in [01-31].
     */
    public boolean isValidDate(String date) {
        return date != null
                && date.matches("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-" +
                "(0[1-9]|[12][0-9]|3[01])$");
    }

    public boolean isValidBase(String base) {
        Set<String> keys = (currencyConverter.getExchangeValues().keySet());
        return base != null
                && base.matches("[A-Z]{3}")
                && keys.contains(base);
    }

    public boolean isValidExpensesDto(ExpensesDTO dto) {
        return isValidDate(dto.getDate())
                && dto.getAmount() > 0
                && isValidBase(dto.getCurrency())
                && dto.getProduct() != null
                && dto.getProduct().length() != 0;
    }
}
