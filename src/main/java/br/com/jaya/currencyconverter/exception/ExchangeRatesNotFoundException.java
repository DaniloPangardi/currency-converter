package br.com.jaya.currencyconverter.exception;

public class ExchangeRatesNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExchangeRatesNotFoundException(String message) {
		super(message);
	}

}
