package rest.webservice.controller;

import java.util.Date;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rest.webservice.dto.Currencies;
import rest.webservice.dto.Currency;
import rest.webservice.dto.CurrencyCode;
import rest.webservice.dto.adapter.DateAdapter;
import rest.webservice.service.CurrencyService;
import rest.webservice.validation.Validator;

@RestController
public class MainController {

	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private DateAdapter dateAdapter;
	@Autowired
	private Validator validator;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Currencies getAll(@QueryParam(value = "date") String date) throws Exception {
		Date parsed = dateAdapter.unmarshal(date);
		validator.validateDate(parsed);
		Currencies allRates = currencyService.getAllRates(parsed);
		return allRates;
	}

	@RequestMapping(value = "/code/{value}", method = RequestMethod.GET)
	public Currency getForCode(@QueryParam(value = "date") String date, @PathVariable String value) throws Exception {
		Date parsed = dateAdapter.unmarshal(date);
		validator.validateDate(parsed);
		return currencyService.getForCode(parsed, CurrencyCode.valueOf(value));
	}

	@RequestMapping(value = "/convertTo", method = RequestMethod.GET)
	public double convertTo(@QueryParam(value = "date") String date, @QueryParam(value = "code") String code,
			@QueryParam(value = "value") double value) throws Exception {
		Date parsed = dateAdapter.unmarshal(date);
		validator.validateDate(parsed);
		validator.validateMoney(value);
		return currencyService.convertTo(parsed, CurrencyCode.valueOf(code), value);
	}
}
