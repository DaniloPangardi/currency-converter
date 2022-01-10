package br.com.jaya.currencyconverter.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.jaya.currencyconverter.dto.ExchangeRatesResponse;

@Service
public class ExchangeRatesService {
	
	@Value("${exchange-rates.url}")
	private String urlBase;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public ExchangeRatesService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public double getExchangeRate(String originCurrency, String destinationCurrency) throws RestClientException, Exception {
		var url = MessageFormat.format(urlBase, originCurrency);
		var response = restTemplate.getForObject(url, ExchangeRatesResponse.class);
		return response.getRate(destinationCurrency);
	}

}