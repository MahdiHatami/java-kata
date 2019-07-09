package com.metis.banking.domain.test;

import static org.junit.Assert.assertThat;

import java.util.Date;

import com.metis.banking.builders.DateCreator;
import com.metis.matchers.DayMonthYearMatcher;
import org.junit.Test;

public class DateCreatorTest {

	@Test public void
	should_create_a_date_from_string() {
		Date date = DateCreator.date("10/01/2012");

		assertThat(date, DayMonthYearMatcher.hasDayMonthYear(10, 1, 2012));
	}

}
