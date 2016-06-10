package rest.webservice.service.impl;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import rest.webservice.dto.Currencies;
import rest.webservice.dto.Currency;
import rest.webservice.dto.CurrencyCode;
import rest.webservice.dto.adapter.DateAdapter;
import rest.webservice.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private static final String EXCHANGE_SERVICE_URL = "http://www.bank.gov.ua/control/uk/curmetal/currency/search";

	@Autowired
	private DateAdapter dateAdapter;

	@Override
	public Currencies getAllRates(Date date) throws Exception {
		return getForDate(date);
	}

	@Override
	public Currency getForCode(Date date, CurrencyCode code) throws Exception {
		Currencies currencies = getForDate(date);
		return currencies.getCurrencies().stream().filter(a -> a.getLetterCode().equals(code.toString())).findFirst()
				.get();
	}

	@Override
	public double convertTo(Date date, CurrencyCode code, double value) throws Exception {
		Currency currency = getForCode(date, code);
		return value / (currency.getExchangeRate() / currency.getNumberOfUnits());

	}

	private Currencies getForDate(Date date) throws Exception {
		RestTemplate template = new RestTemplate();
		URI uri = UriComponentsBuilder.fromHttpUrl(EXCHANGE_SERVICE_URL).queryParam("formType", "searchFormDate")
				.queryParam("time_step", "daily").queryParam("date", dateAdapter.marshal(date))
				.queryParam("outer", "xml").build().encode().toUri();
		Currencies result = template.getForObject(uri, Currencies.class);
		return result;
	}
}
