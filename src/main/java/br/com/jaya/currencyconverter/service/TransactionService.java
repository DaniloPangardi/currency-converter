package br.com.jaya.currencyconverter.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jaya.currencyconverter.dto.TransactionRequest;
import br.com.jaya.currencyconverter.dto.TransactionResponse;
import br.com.jaya.currencyconverter.exception.ResourceNotFoundException;
import br.com.jaya.currencyconverter.model.Transaction;
import br.com.jaya.currencyconverter.repository.TransactionRepository;

@Service
public class TransactionService {

	private ExchangeRatesService exchangeRatesService;
	
	private TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(ExchangeRatesService exchangeRatesService,
			TransactionRepository transactionRepository) {
		this.exchangeRatesService = exchangeRatesService;
		this.transactionRepository = transactionRepository;
	}
	
	public TransactionResponse transaction(TransactionRequest request) {
		
		var exchangeRate =  exchangeRatesService.getExchangeRate(
				request.getOriginCurrency(),
				request.getDestinationCurrency());
		
		var destinationValue = convertOriginValue(exchangeRate, request.getOriginValue());
		
		var transaction = fromRequestToModel(request, exchangeRate, destinationValue);
		
		return fromModelToResponse(transactionRepository.save(transaction));
	}

	private BigDecimal convertOriginValue(double exchangeRate, BigDecimal originValue) {
		return originValue.multiply(BigDecimal.valueOf(exchangeRate))
				.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	private Transaction fromRequestToModel(TransactionRequest request, 
			double exchangeRate,
			BigDecimal destinationValue) {
		
		return Transaction.builder()
				.userId(request.getUserId())
				.originCurrency(request.getOriginCurrency())
				.originValue(request.getOriginValue())
				.destinationCurrency(request.getDestinationCurrency())
				.destinationValue(destinationValue)
				.conversionRateUsed(exchangeRate)
				.dateHourTransaction(LocalDateTime.now(ZoneOffset.UTC))
				.build();
	}
	

	public List<TransactionResponse> getByUserId(Long userId) {
		
		var transactions = transactionRepository.getByUserId(userId);
		
		if(transactions.isEmpty()) {
			throw new ResourceNotFoundException("Resource not found for userId: " + userId);
		}
		
		return transactions.stream()
				.map(t -> fromModelToResponse(t))
				.collect(Collectors.toList());
	}
	
	private TransactionResponse fromModelToResponse(Transaction transaction) {
		var response = new TransactionResponse();
		BeanUtils.copyProperties(transaction, response);
		return response;
	}
	
}