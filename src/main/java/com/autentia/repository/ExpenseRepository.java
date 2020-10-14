package com.autentia.repository;

import com.autentia.model.Expense;
import com.autentia.utils.ConnectionBBDD;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public class ExpenseRepository {

    private ConnectionBBDD bbdd = new ConnectionBBDD();
    private Connection connect;
    private Statement stm;
    private ResultSet rs;
    private String query;

    @Executable
    public List<Expense> findAllExpenses() throws SQLException {

        connect = bbdd.connect();
        stm = connect.createStatement();
        query = "SELECT * from gastos";
        rs = stm.executeQuery(query);
        ArrayList<Expense> expenses = new ArrayList<>();
        while(rs.next()){
            expenses.add(new Expense(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("importe"), rs.getString("descripcion"), rs.getTimestamp("fecha")));
        }
        connect.close();
        rs.close();
        stm.close();
        return expenses;
    }

    @Executable
    public Expense findExpenseById(long id) throws SQLException {

        connect = bbdd.connect();
        stm = connect.createStatement();
        query = "SELECT * FROM gastos WHERE id=" + id;
        rs = stm.executeQuery(query);
        Expense expense = null;
        while(rs.next()){
            expense = new Expense(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("importe"), rs.getString("descripcion"), rs.getTimestamp("fecha"));
        }
        connect.close();
        rs.close();
        stm.close();
        return expense;
    }

    @Executable
    public Expense addExpense(Expense expense) throws SQLException {

        connect = bbdd.connect();
        query = "INSERT INTO gastos (id, nombre, importe, descripcion, fecha) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connect.prepareStatement(query);
        ps.setInt(1, expense.getId());
        ps.setString(2, expense.getNombre());
        ps.setDouble(3, expense.getImporte());
        ps.setString(4, expense.getDescripcion());
        ps.setTimestamp(5, expense.getFecha());
        ps.executeUpdate();
        connect.close();
        ps.close();
        return expense;
    }

}
