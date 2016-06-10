package rest.webservice.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import rest.webservice.dto.adapter.DateAdapter;

/**
 * @author Bohdan_Popyk
 *
 */
@XmlRootElement
public class Currency {

	private Date date;
	private int code;
	private String letterCode;
	private int numberOfUnits;
	private String name;
	private double exchangeRate;

	public Date getDate() {
		return date;
	}

	@XmlElement(name = "date")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public void setDate(Date date) {
		this.date = date;
	}

	public int getCode() {
		return code;
	}

	@XmlElement(name = "digital_code")
	public void setCode(int code) {
		this.code = code;
	}

	public String getLetterCode() {
		return letterCode;
	}

	@XmlElement(name = "letter_code")
	public void setLetterCode(String letterCode) {
		this.letterCode = letterCode;
	}

	public int getNumberOfUnits() {
		return numberOfUnits;
	}

	@XmlElement(name = "number_of_units")
	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public String getName() {
		return name;
	}

	@XmlElement(name = "currency_name")
	public void setName(String name) {
		this.name = name;
	}

	public double getExchangeRate() {
		return exchangeRate;
	}

	@XmlElement(name = "exchange_rate")
	public void setExchangeRate(double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public String toString() {
		return "Currency [date=" + date + ", code=" + code + ", letterCode=" + letterCode + ", numberOfUnits="
				+ numberOfUnits + ", name=" + name + ", exchangeRate=" + exchangeRate + "]";
	}
}
