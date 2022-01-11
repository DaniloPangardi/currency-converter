package br.com.jaya.currencyconverter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jaya.currencyconverter.dto.TransactionRequest;
import br.com.jaya.currencyconverter.exception.ExchangeRatesNotFoundException;

@SpringBootTest
public class TransactionServiceIntegrationTest {
	
	private static final String ORIGIN_CURRENCY = "EUR";
	
	private static final String DESTINATION_CURRENCY = "BRL";
	
	@Autowired
	private TransactionService transactionService;
	
	private TransactionRequest transactionRequest;
	
	@BeforeEach
	public void setup() {
		transactionRequest = buildTransactionRequest(); 
	}
	
	@Test
	void testTransactionWhenCorrectRequest() throws Exception {
		var transactionResponse = transactionService.transaction(transactionRequest);
		assertThat(transactionResponse.getId()).isNotNull();
	}

	@Test
	void testTransactionWhenRequestIsNotCorrect() throws Exception {
		ExchangeRatesNotFoundException assertThrows = 
				Assertions.assertThrows(ExchangeRatesNotFoundException.class,
				() -> {
					transactionRequest.setOriginCurrency(DESTINATION_CURRENCY);;
					transactionService.transaction(transactionRequest);
				});
		assertEquals(ExchangeRatesNotFoundException.class, assertThrows.getClass());
	}
	
	private TransactionRequest buildTransactionRequest() {
		var transactionRequest = new TransactionRequest();
		transactionRequest.setUserId(1L);
		transactionRequest.setOriginCurrency(ORIGIN_CURRENCY);
		transactionRequest.setOriginValue(BigDecimal.valueOf(1));
		transactionRequest.setDestinationCurrency(DESTINATION_CURRENCY);
		return transactionRequest;
	}

}
