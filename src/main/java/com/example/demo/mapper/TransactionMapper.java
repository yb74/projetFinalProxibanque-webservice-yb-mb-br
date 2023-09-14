package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.model.Transaction;

@Component
public class TransactionMapper {
	public TransactionDTO ToDto(Transaction t) {
		return new TransactionDTO(t.getId(), t.getAmount(), t.getTimestamp(), t.getClientEmetteur(),
				t.getClientRecepteur(), t.getTypeDeVirement(), t.getCompteEmitteurId(), t.getCompteRecepteurId());

	}

	public Transaction toTransaction(TransactionDTO tDTO) {
		return new Transaction(tDTO.getId(), tDTO.getAmount(), tDTO.getTimestamp(), tDTO.getClientEmetteur(),
				tDTO.getClientRecepteur(), tDTO.getTypeDeVirement(), tDTO.getCompteEmitteurId(),
				tDTO.getCompteRecepteurId());
	}
}
