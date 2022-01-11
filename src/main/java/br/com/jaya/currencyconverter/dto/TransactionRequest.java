package br.com.jaya.currencyconverter.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TransactionRequest {
	
	@NotNull
	private Long userId;
	
	@NotBlank
	private String originCurrency;
	
	@NotNull
	private BigDecimal originValue;
	
	@NotBlank
	private String destinationCurrency;
	
}
