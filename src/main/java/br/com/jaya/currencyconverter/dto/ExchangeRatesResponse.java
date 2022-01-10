package br.com.jaya.currencyconverter.dto;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.jaya.currencyconverter.exception.ExchangeRatesNotFoundException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRatesResponse {

	private JsonNode rates;
	
	public double getRate(String destinationCurrency) {
		return Optional.ofNullable(rates)
				.orElseThrow(() -> new ExchangeRatesNotFoundException("Exchange rates not found."))
				.get(destinationCurrency)
				.doubleValue();
	}

}