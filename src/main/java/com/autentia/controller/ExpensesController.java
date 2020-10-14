package com.autentia.controller;

import com.autentia.model.Balance;
import com.autentia.service.ExpenseService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import com.autentia.model.Expense;

import javax.inject.Inject;
import java.util.List;

@Controller("/expenses")
public class ExpensesController {

    @Inject
    private final ExpenseService serv;

    @Inject
    public ExpensesController(ExpenseService serv) {
        this.serv = serv;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public List<Expense> findAllExpenses() {
        return serv.findAllExpenses();
    }

    @Get("/{expenseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Expense findExpensesById(long expenseId) {
        return serv.findExpenseById(expenseId);
    }

    @Get("/balance/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Balance> getBalance(int id) {
        return serv.getBalance(id);
    }

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    public Expense addExpense(@Body Expense expense) {
        return serv.addExpense(expense.getId(), expense.getNombre(), expense.getImporte(), expense.getDescripcion(), expense.getFecha());
    }

}