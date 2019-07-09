package com.metis.banking.domain.test;

import static com.metis.banking.builders.TransactionBuilder.aTransaction;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import java.util.Date;

import com.metis.banking.builders.DateCreator;
import com.metis.banking.domain.Account;
import com.metis.banking.domain.Amount;
import com.metis.banking.domain.Statement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

	@Mock private Statement statement;
	private Account account;

	@Before
	public void initialise() {
		account = Account.createAccount(statement);
	}

	@Test public void
	should_add_deposit_line_to_statement() {
		Date depositDate = DateCreator.date("10/01/2012");
		Amount depositAmount = Amount.amountOf(1000);

		account.deposit(depositAmount, depositDate);

		verify(statement).addLineContaining(aTransaction()
												.with(depositDate)
												.with(depositAmount).build(),
											currentBalanceEqualsTo(depositAmount));
	}

	@Test public void
	should_add_withdraw_line_to_statement() {
		Date withdrawalDate = DateCreator.date("12/01/2012");

		account.withdrawal(Amount.amountOf(500), withdrawalDate);

		verify(statement).addLineContaining(aTransaction()
											.with(Amount.amountOf(-500))
											.with(withdrawalDate).build(),
											Amount.amountOf(-500));
	}

	@Test public void
	should_print_statement() {
		PrintStream printer = System.out;

		account.printStatement(printer);

		verify(statement).printTo(printer);
	}

	private Amount currentBalanceEqualsTo(Amount amount) {
		return amount;
	}

}
