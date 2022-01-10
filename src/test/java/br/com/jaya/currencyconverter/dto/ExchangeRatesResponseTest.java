package br.com.jaya.currencyconverter.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jaya.currencyconverter.dto.ExchangeRatesResponse;

public class ExchangeRatesResponseTest {
	
	private static final String EMPTY_STRING = "";
	
	private static final String RATES = "{\"BRL\" : 6.403228, \"EUR\": 1}";
	
	private static final String REAL_CURRENCY = "BRL";
	
	private static final double REAL_EXCHANGE_RATE = 6.403228;

	private ExchangeRatesResponse exchangeRatesResponse;
	
	private JsonNode rates;
	
	@BeforeEach
	public void setup() throws Exception {
		 exchangeRatesResponse = new ExchangeRatesResponse();
		 rates = new ObjectMapper().readTree(RATES);
	}
	
    @Test
	void testExpectedExceptionFail() throws Exception {
    	NoSuchElementException assertThrows = Assertions.assertThrows(NoSuchElementException.class, () -> {
    		exchangeRatesResponse.getRate(EMPTY_STRING);
    	});
    	assertEquals(NoSuchElementException.class, assertThrows.getClass());
	}

    @Test
	void testExchangeRateWhenRealCurrency() throws Exception {
    	exchangeRatesResponse.setRates(rates);
    	assertEquals(REAL_EXCHANGE_RATE, exchangeRatesResponse.getRate(REAL_CURRENCY));
	}

}
