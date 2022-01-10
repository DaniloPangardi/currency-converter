package br.com.jaya.currencyconverter.dto;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ExchangeRatesResponse {

	private boolean success;
	
	@Setter
	private JsonNode rates;
	
	public double getRate(String destinationCurrency) {
		return Optional.ofNullable(rates)
				.orElseThrow()
				.get(destinationCurrency)
				.doubleValue();
	}

}