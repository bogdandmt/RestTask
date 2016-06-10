package rest.webservice.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import rest.webservice.dto.Currencies;
import rest.webservice.dto.Currency;
import rest.webservice.dto.CurrencyCode;

@Service
public interface CurrencyService {

	public Currencies getAllRates(Date date) throws Exception;

	public Currency getForCode(Date date, CurrencyCode code) throws Exception;

	public double convertTo(Date date, CurrencyCode code, double value) throws Exception;
}
