package br.com.jaya.currencyconverter.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "tb_transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String originCurrency;
	private BigDecimal originValue;
	private String destinationCurrency;
	private BigDecimal destinationValue;
	private double conversionRateUsed;
    private LocalDateTime dateHourTransaction;

}
