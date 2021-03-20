package com.shymoniak.expenses.tools;

import com.shymoniak.expenses.domain.ExpensesDTO;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    /**
     * @param date
     * @return true if date consists of numbers in YYYY-MM-DD format, has
     * limited years in a range of [1900 – 2999], months in [01-12] and days
     * in [01-31].
     */
    public boolean isValidDate(String date) {
        return date.matches("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-" +
                "(0[1-9]|[12][0-9]|3[01])$");
    }

    public boolean isValidExpensesDto(ExpensesDTO dto){
        return isValidDate(dto.getDate())
                && dto.getAmount() > 0
                && dto.getCurrency() != null
                && dto.getCurrency().matches("[A-Z]{3}")
                && dto.getProduct() != null
                && dto.getProduct().length() != 0;
    }
}
