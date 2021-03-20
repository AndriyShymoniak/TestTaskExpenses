package com.shymoniak.expenses.constant;

import java.util.TimeZone;

public class ApiConstants {
    public static final TimeZone SERVER_TIME_ZONE
            = TimeZone.getTimeZone("Europe/Kiev"); //UTC+2
    public static final TimeZone USER_TIME_ZONE =
            TimeZone.getTimeZone("Iceland"); //UTC+0 [For Postman]
    public static final String DATE_FORMAT = "yyyy-MM-dd";
}
