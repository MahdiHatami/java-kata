package com.metis.banking.builders;

import java.util.Date;

import com.metis.banking.domain.Amount;
import com.metis.banking.domain.Transaction;

public class TransactionBuilder {

	private Date date;
	private Amount value;

	public static TransactionBuilder aTransaction() {
		return new TransactionBuilder();
	}

	public TransactionBuilder with(Date date) {
		this.date = date;
		return this;
	}

	public TransactionBuilder with(Amount value) {
		this.value = value;
		return this;
	}

	public Transaction build() {
		Transaction transaction = new Transaction(value, date);
		return transaction;
	}



}
