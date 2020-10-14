package com.autentia.service;

import com.autentia.model.Balance;
import com.autentia.model.Expense;
import com.autentia.model.Friend;
import com.autentia.model.Group;
import com.autentia.repository.ExpenseRepository;
import com.autentia.repository.FriendRepository;
import com.autentia.repository.GroupRepository;
import io.micronaut.http.exceptions.HttpStatusException;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private FriendRepository friendRepository;
    private GroupRepository groupRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Balance> getBalance(int id) {

        List<Balance> balanceList = null;
        List<Expense> expenseList = null;
        List<Friend> friendList = null;
        List<Friend> friendListAux = null;
        List<Group> groupList = null;
        friendRepository = new FriendRepository();
        groupRepository = new GroupRepository();
        double payment = 0;
        int group_id = 0;
        double cont = 0;
        String name = "";
        Expense expense = null;
        Group group = null;

        try {
            // SACAMOS LOS NOMBRES DE LOS AMIGOS QUE HAN PAGADO
            expenseList = expenseRepository.findAllExpenses();
            for (Expense e:expenseList) {
                if(id == e.getId()) {
                    payment = e.getImporte();
                    name = e.getNombre();
                    System.out.println("ID " + e.getId() + "Friend_Name: " + e.getNombre() + " Bill: " + e.getImporte());
                    expense = new Expense(e.getId(), e.getNombre(), e.getImporte(), e.getDescripcion(), e.getFecha());
                }
            }
            // SACAMOS LOS NOMBRES QUE COINCIDAN CON LOS PAGOS PARA SABER DE QUE GRUPO FORMAN PARTE
            friendList = friendRepository.findAllFriends();
            friendListAux = new ArrayList<>();
            for (Friend f:friendList) {
                if(name.equals(f.getNombre())) {
                    group_id = f.getId_grupo();
                }
                if(group_id == f.getId_grupo()) {
                    cont++;
                    if(!name.equals(f.getNombre())) {
                        System.out.println("Name: " + f.getNombre() + " Group_id: " + f.getId_grupo());
                        friendListAux.add(new Friend(f.getId(), f.getNombre(), f.getId_grupo()));
                    }
                }
            }
            // SACAMOS LOS GRUPOS
            groupList = groupRepository.findAllGroups();
            for (Group g:groupList) {
                if(group_id == g.getId_grupo()) {
                    System.out.println("Group_name: " + g.getNombre_grupo() + " Group_id: " + g.getId_grupo());
                    group = new Group(g.getId_grupo(), g.getNombre_grupo());
                }
            }
            payment /= cont;
            System.out.println("Cada amigo del grupo " + group_id + " debe " + payment + "€");
            // AÑADIMOS LOS DATOS A LA LISTA DE BALANCE
            balanceList = new ArrayList<>();
            for (Friend f: friendListAux) {
                balanceList.add(new Balance(expense.getId(), group.getNombre_grupo(), f.getNombre(), payment, 0));
            }
        } catch (SQLException throwables) {
            System.out.println("ERROR: balance could not be achieved. \n" + throwables.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return balanceList;
    }

    public List<Expense> findAllExpenses() {
        List<Expense> expenseList = null;
        try {
            expenseList = expenseRepository.findAllExpenses();
        } catch (SQLException findAllExpensesException) {
            System.out.println("ERROR: expenses could not be found. \n" + findAllExpensesException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return expenseList;
    }

    public Expense findExpenseById(long id) {
        Expense expense = null;
        try {
            expense = expenseRepository.findExpenseById(id);
        } catch (SQLException findExpenseByIdException) {
            System.out.println("ERROR: expense " + id + " could not be found. \n" + findExpenseByIdException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return expense;
    }

    public Expense addExpense(long id, String name, double bill, String description, Timestamp date) {
        Expense expense = null;
        try {
            expense = expenseRepository.addExpense(new Expense((int)id, name, bill, description, date));
        } catch (SQLException addExpenseException) {
            System.out.println("Error, expense could not be added. \n" + addExpenseException.getMessage());
        } catch (HttpStatusException statusException) {
            System.out.println(statusException.getStatus());
        }
        return expense;
    }

}
