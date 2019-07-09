package com.metis.banking.domain.test;

import static com.metis.banking.builders.TransactionBuilder.aTransaction;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import com.metis.banking.builders.DateCreator;
import com.metis.banking.domain.Amount;
import com.metis.banking.domain.Statement;
import com.metis.banking.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementTest {

	@Mock PrintStream printer;
	@Mock
	Transaction transaction;
	private Statement statement;

	@Before
	public void initialise() {
		statement = new Statement();
	}

	@Test public void
	should_print_statement_header() {
		statement.printTo(printer);

		verify(printer).println(Statement.STATEMENT_HEADER);
	}

	@Test public void
	should_print_deposit() {
		statement.addLineContaining(aTransaction()
										.with(Amount.amountOf(1000))
										.with(DateCreator.date("10/01/2012")).build(),
										Amount.amountOf(1000));

		statement.printTo(printer);

		verify(printer).println("10/01/2012 | 1000.00  |          | 1000.00");
	}

	@Test public void
	should_print_withdrawal() {
		statement.addLineContaining(aTransaction()
										.with(Amount.amountOf(-1000))
										.with(DateCreator.date("10/01/2012")).build(),
										Amount.amountOf(-1000));

		statement.printTo(printer);

		verify(printer).println("10/01/2012 |          | 1000.00  | -1000.00");
	}

	@Test public void
	should_print_two_deposits_in_reverse_order() {
		statement.addLineContaining(aTransaction()
										.with(Amount.amountOf(1000))
										.with(DateCreator.date("10/01/2012")).build(),
										Amount.amountOf(1000));
		statement.addLineContaining(aTransaction()
										.with(Amount.amountOf(2000))
										.with(DateCreator.date("13/01/2012")).build(),
										Amount.amountOf(3000));

		statement.printTo(printer);

		InOrder inOrder = Mockito.inOrder(printer);
		inOrder.verify(printer).println("13/01/2012 | 2000.00  |          | 3000.00");
		inOrder.verify(printer).println("10/01/2012 | 1000.00  |          | 1000.00");

	}

}
