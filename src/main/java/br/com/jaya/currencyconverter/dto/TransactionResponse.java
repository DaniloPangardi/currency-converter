package br.com.jaya.currencyconverter.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {
	private Long id;
	private Long userId;
	private String originCurrency;
	private BigDecimal originValue;
	private String destinationCurrency;
	private BigDecimal destinationValue;
	private double conversionRateUsed;
    private LocalDateTime dateHourTransaction;
}
