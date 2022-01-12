package br.com.jaya.currencyconverter.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jaya.currencyconverter.dto.ExchangeRatesResponse;

@Service
public class ExchangeRatesService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRatesService.class);
	
	@Value("${exchange-rates.url}")
	private String urlBase;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public ExchangeRatesService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public double getExchangeRate(String originCurrency, String destinationCurrency) {
		var url = MessageFormat.format(urlBase, originCurrency);
		LOG.info("Base currency: {} and destination currency {}. Getting exchange rate...", originCurrency, destinationCurrency);
		var response = restTemplate.getForObject(url, ExchangeRatesResponse.class);
		return response.getRate(destinationCurrency);
	}

}