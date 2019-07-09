package com.metis.banking;

import com.metis.banking.builders.DateCreator;
import com.metis.banking.domain.Account;
import com.metis.banking.domain.Amount;
import com.metis.banking.domain.Statement;

public class StartApp
{

    public static void main(String[] args)
    {
        Account account = Account.createAccount(new Statement());

        account.deposit(Amount.amountOf(1000), DateCreator.date("10/01/2012"));
        account.deposit(Amount.amountOf(2000), DateCreator.date("13/01/2012"));
        account.withdrawal(Amount.amountOf(500), DateCreator.date("14/01/2012"));

        account.printStatement(System.out);
    }

}
