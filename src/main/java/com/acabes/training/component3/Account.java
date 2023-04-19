package com.acabes.training.component3;

public class Account {
    private final int accountId;
    private final String name;

    public Account( int accountId,String name) {
        this.name = name;
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }


    public int getAccountId() {
        return accountId;
    }

}
