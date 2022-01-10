package br.com.jaya.currencyconverter.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import br.com.jaya.currencyconverter.dto.TransactionRequest;
import br.com.jaya.currencyconverter.dto.TransactionResponse;

@Service
public class TransactionService {

	private ExchangeRatesService exchangeRatesService;
	
	@Autowired
	public TransactionService(ExchangeRatesService exchangeRatesService) {
		this.exchangeRatesService = exchangeRatesService;
	}
	
	public TransactionResponse transaction(TransactionRequest request) throws RestClientException, Exception {
		
		var exchangeRate =  exchangeRatesService.getExchangeRate(request.getOriginCurrency(),
				request.getDestinationCurrency());
		
		var response = new TransactionResponse();
		response.setDestinationValue(convertCurrency(exchangeRate, request.getOriginValue()));
		return response;
	}
	
	private BigDecimal convertCurrency(double exchangeRate, BigDecimal originValue) {
		return originValue.multiply(BigDecimal.valueOf(exchangeRate));
	}
}