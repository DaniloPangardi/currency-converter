package br.com.jaya.currencyconverter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.jaya.currencyconverter.dto.TransactionRequest;
import br.com.jaya.currencyconverter.exception.ResourceNotFoundException;
import br.com.jaya.currencyconverter.model.Transaction;
import br.com.jaya.currencyconverter.repository.TransactionRepository;

public class TransactionServiceTest {
	
	
	private static final String ORIGIN_CURRENCY = "EUR";
	
	private static final String DESTINATION_CURRENCY = "BRL";
	
	private static final double EXCHANGE_RATE = 2.53;
	
	private static final Long ID_DEFAULT = 1L;
	
	@InjectMocks
	private TransactionService transactionService;
	
	@Mock
    private ExchangeRatesService exchangeRatesService;
	
	@Mock
	private TransactionRepository transactionRepository;
	
	private TransactionRequest transactionRequest;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		transactionRequest = buildTransactionRequest(); 
	}
	
    @Test
	void testConvertOriginValue() throws Exception {
    	when(exchangeRatesService.getExchangeRate(Mockito.any(), Mockito.any()))
    	.thenReturn(EXCHANGE_RATE);

    	when(transactionRepository.save(Mockito.any())).thenReturn(new Transaction());
    	
    	var transactionResponse = transactionService.transaction(transactionRequest);
    	
    	var destinationValue = transactionResponse.getDestinationValue();
    	
    	assertEquals(7.59, destinationValue.doubleValue());
	}
    
    
    @Test
	void testGetByUserIdWhenResourceFound() throws Exception {
    	var transaction = new Transaction();
    	transaction.setId(ID_DEFAULT);
		
    	when(transactionRepository.getByUserId(Mockito.any()))
		.thenReturn(Arrays.asList(transaction));
    	
    	var transactions = transactionService.getByUserId(ID_DEFAULT);
    	
    	assertEquals(transactions.get(0).getId(), ID_DEFAULT);
	}
    
    @Test
   	void testGetByUserIdWhenResourceNotFound() throws Exception {
		
    	when(transactionRepository.getByUserId(Mockito.any()))
		.thenReturn(Collections.emptyList());
    	
		ResourceNotFoundException assertThrows = 
				Assertions.assertThrows(ResourceNotFoundException.class,
				() -> {
					transactionService.getByUserId(ID_DEFAULT);
				});
		assertEquals(ResourceNotFoundException.class, assertThrows.getClass());
   	}
	
	private TransactionRequest buildTransactionRequest() {
		var transactionRequest = new TransactionRequest();
		transactionRequest.setUserId(ID_DEFAULT);
		transactionRequest.setOriginCurrency(ORIGIN_CURRENCY);
		transactionRequest.setOriginValue(BigDecimal.valueOf(3));
		transactionRequest.setDestinationCurrency(DESTINATION_CURRENCY);
		return transactionRequest;
	}

}
