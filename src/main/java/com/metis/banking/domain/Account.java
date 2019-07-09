package com.metis.banking.domain;

import java.io.PrintStream;
import java.util.Date;

public class Account
{
    private Statement statement;

    private Amount balance = Amount.amountOf(0);

    private Account(Statement statement)
    {
        this.statement = statement;
    }

    public static Account createAccount(Statement statement)
    {
        return new Account(statement);
    }

    public void deposit(Amount value, Date date)
    {
        recordTransaction(value, date);
    }

    public void withdrawal(Amount value, Date date)
    {
        recordTransaction(value.negative(), date);
    }

    public void printStatement(PrintStream printer)
    {
        statement.printTo(printer);
    }

    private void recordTransaction(Amount value, Date date)
    {
        Transaction transaction = new Transaction(value, date);
        Amount balanceAfterTransaction = transaction.balanceAfterTransaction(balance);
        balance = balanceAfterTransaction;
        statement.addLineContaining(transaction, balanceAfterTransaction);
    }

}
