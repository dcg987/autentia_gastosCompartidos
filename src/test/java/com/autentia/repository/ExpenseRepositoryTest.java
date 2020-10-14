package com.autentia.repository;

import com.autentia.model.Expense;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ExpenseRepositoryTest {

    @Inject
    ExpenseRepository expenseRepository;

    @Test
    void findAllExpenses() throws SQLException {

        List<Expense> expenseList = expenseRepository.findAllExpenses();
        assertFalse(expenseList.isEmpty());
    }

    @Test
    void findExpenseById() throws SQLException {

        List<Expense> expenseList = expenseRepository.findAllExpenses();
        expenseList = Collections.singletonList(expenseRepository.findExpenseById(1));
        assertTrue(expenseList.get(0).getId() == 1);
    }

    @Test
    void addExpense() throws SQLException {

        List<Expense> expenseList = expenseRepository.findAllExpenses();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Expense expense = new Expense(5, "Patricia", 75, "Merienda", timestamp);
        expenseRepository.addExpense(expense);
        assertFalse(expenseList.isEmpty());
    }
}