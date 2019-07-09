package com.metis.banking.domain.test;

import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import com.metis.banking.builders.DateCreator;
import com.metis.banking.builders.TransactionBuilder;
import com.metis.banking.domain.Amount;
import com.metis.banking.domain.StatementLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementLineTest {

	@Mock PrintStream printer;

	@Test public void
	should_print_itself() {
		StatementLine statementLine = new StatementLine(
											TransactionBuilder.aTransaction()
												.with(Amount.amountOf(1000))
												.with(DateCreator.date("10/01/2012")).build(),
											Amount.amountOf(1000));

		statementLine.printTo(printer);

		verify(printer).println("10/01/2012 | 1000.00  |          | 1000.00");
	}

	@Test public void
	should_print_withdrawal() {
		StatementLine statementLine = new StatementLine(
											TransactionBuilder.aTransaction()
												.with(Amount.amountOf(-1000))
												.with(DateCreator.date("10/01/2012")).build(),
											Amount.amountOf(-1000));

		statementLine.printTo(printer);

		verify(printer).println("10/01/2012 |          | 1000.00  | -1000.00");
	}


}
