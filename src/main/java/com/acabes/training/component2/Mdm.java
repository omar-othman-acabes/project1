package com.acabes.training.component2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mdm {

    private final ArrayList<Transaction> initialTransactions;
    private final ArrayList<FullTransaction> fullTransactions;
    private final ArrayList<String> names;
    Random random = new Random();
    private final HashMap<Integer, String> uniqueNames;

    public Mdm(ArrayList<Transaction> initialTransactions, ArrayList<String> names) throws IOException {

        this.initialTransactions = initialTransactions;
        this.names = names;
        this.uniqueNames = new HashMap<>();

        fullTransactions = new ArrayList<>();
        getFullTransactionData();
        new Writer(fullTransactions);
    }

    public void getFullTransactionData() {
        for (Transaction transaction : initialTransactions) {
            createFullTransaction(transaction);
        }

    }

    private void createFullTransaction(Transaction transaction) {
        int fromAccountID = transaction.getFromAccountNumber();
        int toAccountID = transaction.getToAccountNumber();
        double amount = transaction.getAmount();

        String fromAccountName = getFromAccountName(toAccountID);
        String toAccountName = getToAccountName( fromAccountID);

        fullTransactions.add(new FullTransaction(fromAccountID, toAccountID, amount, fromAccountName, toAccountName));
    }

    private String getToAccountName(int toAccountID) {
        if (uniqueNames.containsKey(toAccountID))
            return uniqueNames.get(toAccountID);

        else {
            String randomElement = names.get(random.nextInt(names.size()));
            uniqueNames.put(toAccountID, randomElement);
            return randomElement;
        }

    }

    private String getFromAccountName( int fromAccountID) {

       if (uniqueNames.containsKey(fromAccountID))
            return uniqueNames.get(fromAccountID);

        else {
            String randomElement = names.get(random.nextInt(names.size()));
            uniqueNames.put(fromAccountID, randomElement);
            return randomElement;
        }

    }


}
