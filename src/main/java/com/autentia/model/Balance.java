package com.autentia.model;

public class Balance {

    private int expenseId;
    private String groupName;
    private String friendName;
    private double bill;
    private double paid;

    public Balance(int expenseId, String groupName, String friendName, double bill, double paid) {
        this.expenseId = expenseId;
        this.groupName = groupName;
        this.friendName = friendName;
        this.bill = bill;
        this.paid = paid;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double isPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
