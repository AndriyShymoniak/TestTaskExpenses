package com.shymoniak.expenses.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static com.shymoniak.expenses.constant.ApiConstants.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat(DATE_FORMAT);

    private Long id;
    private String date;
    private double amount;
    private String currency;
    private String product;

    public Instant convertDate() throws ParseException {
        dateFormat.setTimeZone(USER_TIME_ZONE);
        return dateFormat.parse(this.date).toInstant();
    }

    public void setDate(Date date) {
        dateFormat.setTimeZone(SERVER_TIME_ZONE);
        this.date = dateFormat.format(date);
    }
}
