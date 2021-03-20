package com.shymoniak.expenses;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

//@SpringBootTest
class ExpensesApplicationTests {

	@Test
	void contextLoads() {
		OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
		Date date = Date.from(utc.toInstant());
		long epochMillis = utc.toInstant().toEpochMilli();
		System.out.println(utc);
		System.out.println(date);
		System.out.println(epochMillis);
		Instant instant = Instant.now();
		System.out.println(instant);
	}

}
