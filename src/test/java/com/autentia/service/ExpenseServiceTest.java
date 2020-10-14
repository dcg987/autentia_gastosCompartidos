package com.autentia.service;

import com.autentia.model.Expense;
import com.autentia.repository.ExpenseRepository;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@MicronautTest
class ExpenseServiceTest {

    @Mock
    private final ExpenseRepository mockRepository = mock(ExpenseRepository.class);
    private final ExpenseService sut = new ExpenseService(mockRepository);
    private final int ID = 2;
    private final String NAME = "Sara";
    private final double BILL = 100;
    private final String DESCRIPTION = "Cena";
    private final Timestamp DATE = new Timestamp(System.currentTimeMillis());;

    @Test
    void shouldBeFindAllExpenses() {
        try {
            final List<Expense> expenses = new ArrayList<>();
            expenses.add(new Expense(ID, NAME, BILL, DESCRIPTION, DATE));
            expenses.add(new Expense((ID + 1), NAME, 50, "Merienda", DATE));
            doReturn(expenses).when(mockRepository).findAllExpenses();
            final List<Expense> expenses2 = sut.findAllExpenses();
            assertThat(expenses2, is(equalTo(expenses)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    void shouldBeFindExpenseById() {
        try {
            final Expense expense = new Expense(ID, NAME, BILL, DESCRIPTION, DATE);
            doReturn(expense).when(mockRepository).findExpenseById(ID);
            final Expense expense2 = sut.findExpenseById(ID);
            assertThat(expense2, is(equalTo(expense)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    void shouldBeAddExpense() {
        try {
            final Expense expense = new Expense(ID, NAME, BILL, DESCRIPTION, DATE);
            Expense expense1 = null;
            expense1 = doReturn(expense).when(mockRepository).addExpense(expense);
            final Expense expense2 = sut.addExpense(ID, NAME, BILL, DESCRIPTION, DATE);
            assertThat(expense2, is(equalTo(expense1)));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}