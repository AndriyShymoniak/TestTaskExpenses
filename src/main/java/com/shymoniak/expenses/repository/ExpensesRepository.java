package com.shymoniak.expenses.repository;

import com.shymoniak.expenses.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.time.Instant;


@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    @Transactional
    void deleteAllByDateBetween(Instant from, Instant to);
}
