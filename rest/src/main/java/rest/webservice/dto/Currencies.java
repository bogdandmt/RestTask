package rest.webservice.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Currencies {

	private List<Currency> currencies;

	public List<Currency> getCurrencies() {
		return currencies;
	}

	@XmlElement(name = "currency")
	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
}
