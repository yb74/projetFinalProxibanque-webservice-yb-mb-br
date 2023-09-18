package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.Client;
import com.example.demo.model.Transaction;
import com.example.demo.service.ClientService;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final TransactionService transactionService;
	private final ClientService clientService;

	@Autowired
	private TransactionMapper mapper;

	public TransactionController(TransactionService transactionService, ClientService clientService) {
		this.transactionService = transactionService;
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<TransactionDTO>> getAllTransactions() throws GeneralException {
		return new ResponseEntity<>(transactionService.getAllTransactions().stream().map(t -> mapper.ToDto(t)).toList(),
				HttpStatus.OK);
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<TransactionDTO> getTransaction(@PathVariable Long transactionId) throws GeneralException {
		Optional<Transaction> Optransaction = transactionService.getTransactionById(transactionId);

		if (Optransaction.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 Not Found if the client doesn't exist
		}
		TransactionDTO transactionDTO = mapper.ToDto(Optransaction.get());
		return ResponseEntity.ok(transactionDTO);
	}

	@PutMapping("ComptesCourants")
	ResponseEntity<String> virementCompteCourantToCompteCourant(@RequestParam Long idEmetteur,
			@RequestParam Long idRecepteur, @RequestParam double montant) throws GeneralException {
		return new ResponseEntity<>(transactionService.virementComptesCourants(montant, idEmetteur, idRecepteur),
				HttpStatus.OK);
	}

	@PutMapping("CourantEpargne")
	public ResponseEntity<String> virement(@RequestParam Long idEmetteur, @RequestParam double montant,
			@RequestParam String typeVirement) throws GeneralException {
		switch (typeVirement) {
		case "compteCourantVersCompteEpargne": {
			return ResponseEntity.ok(transactionService.virementCourantEpargne(montant, idEmetteur));
		}
		case "compteEpargneVersCompteCourant": {
			return ResponseEntity.ok(transactionService.virementEpargneCourant(montant, idEmetteur));
		}
		default:
			throw new GeneralException("Type de virement non pris en charge: " + typeVirement);
		}
	}

	// Exception handler for GeneralException
	@ExceptionHandler(GeneralException.class)
	public ResponseEntity<String> handleGeneralException(GeneralException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// Method that handles validation exceptions
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		HashMap<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(e -> {
			String fieldName = ((FieldError) e).getField();
			String errorMessage = e.getDefaultMessage();

			errors.put(fieldName, errorMessage);
		});

		return errors;
	}
}
