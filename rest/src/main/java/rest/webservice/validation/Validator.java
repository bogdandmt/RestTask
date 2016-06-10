package rest.webservice.validation;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public interface Validator {

	void validateDate(Date value);

	void validateMoney(double value);
}
