package br.com.jaya.currencyconverter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import br.com.jaya.currencyconverter.dto.TransactionRequest;
import br.com.jaya.currencyconverter.dto.TransactionResponse;
import br.com.jaya.currencyconverter.service.TransactionService;

@RestController
@RequestMapping("transactions")
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TransactionResponse transaction(@RequestBody TransactionRequest transactionRequest) throws RestClientException, Exception {
		return transactionService.transaction(transactionRequest);
	}
	
	@GetMapping("{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<TransactionResponse> getByUserId(@PathVariable Long userId) {
		return transactionService.getByUserId(userId);
	}
}