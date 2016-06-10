package rest.webservice.client;

import java.net.URI;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Client {

	private static final String URL = "http://localhost:8090";

	public static void main(String[] args) {

		Client client = new Client();
		client.testConvert();
		client.testGetForCode();
		client.testGetAll();
	}

	private void testConvert() {
		URI uri = UriComponentsBuilder.fromHttpUrl(URL).path("convertTo").queryParam("date", "10.06.2016")
				.queryParam("code", "USD").queryParam("value", 2500).build().encode().toUri();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(new MediaType("application", "json")));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

		System.out.println();
		System.out.println(res.getBody());
		System.out.println();
	}

	private void testGetForCode() {
		URI uri = UriComponentsBuilder.fromHttpUrl(URL).path("code/").path("AUD").queryParam("date", "10.06.2016")
				.build().encode().toUri();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(new MediaType("application", "json")));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

		System.out.println("\nContent type: " + res.getHeaders().getContentType());
		System.out.println(res.getBody());
		System.out.println();
	}

	private void testGetAll() {
		URI uri = UriComponentsBuilder.fromHttpUrl(URL).path("all").queryParam("date", "10.06.2016").build().encode()
				.toUri();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(new MediaType("application", "xml")));

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);

		System.out.println("\nContent type: " + res.getHeaders().getContentType());
		System.out.println(res.getBody());
		System.out.println();
	}
}
