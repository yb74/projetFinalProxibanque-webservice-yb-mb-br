package com.example.demo.controller;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.CompteCourant;
import com.example.demo.model.CompteEpargne;
import com.example.demo.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @GetMapping("/comptes/courants")
    public ResponseEntity<List<CompteCourantDTO>> getComptesCourantsDebiteurs() {
        List<CompteCourantDTO> comptesCourantsDébiteurs = auditService.getComptesCourantsDebiteurs();

        if (comptesCourantsDébiteurs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(comptesCourantsDébiteurs);
        }
    }

    @GetMapping("/comptes/epargnes")
    @ResponseBody
    public ResponseEntity<List<CompteEpargneDTO>> getComptesEpargneDebiteurs() {
        List<CompteEpargneDTO> comptesEpargneDebiteurs = auditService.getComptesEpargneDebiteurs();

        if (comptesEpargneDebiteurs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(comptesEpargneDebiteurs);
        }
    }

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
