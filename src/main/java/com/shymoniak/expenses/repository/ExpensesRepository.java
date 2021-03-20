package com.shymoniak.expenses.repository;

import com.shymoniak.expenses.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
//    void deleteAllByDateEquals(OffsetDateTime date);
//    void deleteAllByDateBetween(OffsetDateTime from, OffsetDateTime to);
}
