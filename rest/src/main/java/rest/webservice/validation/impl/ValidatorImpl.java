package rest.webservice.validation.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

import rest.webservice.validation.Validator;

@Component
public class ValidatorImpl implements Validator {

	private static final Date START = Date
			.from(LocalDate.of(1996, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());

	@Override
	public void validateDate(Date value) {

		Date now = new Date();
		if (value.after(now)) {
			throw new IllegalArgumentException("Future date");
		}
		if (value.before(START)) {
			throw new IllegalArgumentException("The date is too old");
		}
	}

	@Override
	public void validateMoney(double value) {
		if (value < 0D) {
			throw new IllegalArgumentException("Invalid data");
		}
	}
}
