package br.com.jaya.currencyconverter.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class TransactionRequest {
	private long userId;
	private String originCurrency;
	private BigDecimal originValue;
	private String destinationCurrency;
}
