package rest.webservice.dto.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.stereotype.Component;

@Component
public class DateAdapter extends XmlAdapter<String, Date> {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	@Override
	public String marshal(Date v) throws Exception {
		return dateFormat.format(v);
	}

	@Override
	public Date unmarshal(String v) throws Exception {
		return dateFormat.parse(v);
	}
}
