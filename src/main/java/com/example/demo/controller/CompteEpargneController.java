package com.example.demo.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.dto.CompteEpargneDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.service.CompteEpargneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comptes/epargnes")
public class CompteEpargneController {

    /**
     * Service pour gérer les opérations sur les comptes.
     */
    @Autowired
    private CompteEpargneService compteService;

//    @Autowired
//    private TransferCashService transferCashService;

    /**
     * Récupère tous les comptes existants.
     *
     * @return Une liste de comptes (Iterable<Compte>).
     * @throws GeneralException
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CompteEpargneDTO>> getAllComptesEpargnes() {
        List<CompteEpargneDTO> comptesEpargnes = compteService.getAllCompte();
        if (comptesEpargnes.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(comptesEpargnes, HttpStatus.OK);
        }
    }
    
    @GetMapping("/client/{clientId}")
    ResponseEntity<CompteEpargneDTO> getCompteByClientId(@PathVariable Long clientId) throws GeneralException {
        CompteEpargneDTO compte = compteService.getCompteByClientId(clientId).orElse(null);

		if (compte != null) {
		    return new ResponseEntity<>(compte, HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    } 

    /**
     * Crée un nouveau compte.
     *
     * @param compte Le compte à créer (RequestBody).
     * @return Le compte créé.
     */
//	@PostMapping
//	CompteCourantDTO postCompte(@Valid @RequestBody CompteCourantDTO compte) {
//		return compteService.createCompte(compte);
//	}

    /**
     * Crée un nouveau compte épargne et associe un client.
     *
     * @param CompteEpargneDTO Le compte à créer et le client associé (RequestBody).
     * @return Le compte créé.
     */
    @PostMapping
    ResponseEntity<CompteEpargneDTO> createCompteWithClient(@Valid @RequestBody CompteEpargneDTO createCompteEpargneDTO) throws GeneralException {
        CompteEpargneDTO createdCompteCourant = compteService.createCompteWithClient(createCompteEpargneDTO);

        return new ResponseEntity<>(createdCompteCourant, HttpStatus.CREATED);
    }

    /**
     * Récupère un compte par son identifiant.
     *
     * @param id L'identifiant du compte à récupérer (PathVariable).
     * @return Le compte correspondant s'il existe (Optional<Compte>), sinon un
     *         Optional vide.
     * @throws GeneralException
     */
    @GetMapping("/{id}")
    CompteEpargneDTO getCompteById(@PathVariable Long id) throws GeneralException {
        return compteService.getCompteById(id);
    }
    
    @GetMapping("/NumCompte")
    Optional<CompteEpargneDTO> getCompteByAccountNumber(@RequestParam String AccountNumber) throws GeneralException {
        return compteService.getCompteByAccountNumber(AccountNumber);
    }

    /**
     * Met à jour un compte existant ou crée un nouveau compte s'il n'existe pas.
     *
     * @param id     L'identifiant du compte à mettre à jour (PathVariable).
     * @param compte Le compte avec les nouvelles données (RequestBody).
     * @return Une ResponseEntity contenant le compte mis à jour (HttpStatus OK)
     *         s'il existait déjà, ou le nouveau compte créé (HttpStatus CREATED)
     *         s'il n'existait pas.
     * @throws GeneralException
     */
    @PutMapping("/{id}")
    ResponseEntity<CompteEpargneDTO> updateCompte(@PathVariable Long id, @RequestBody CompteEpargneDTO compte) throws GeneralException {
        return new ResponseEntity<>(compteService.updateCompte(id, compte), HttpStatus.OK);
    }

    /**
     * Supprime un compte par son identifiant.
     *
     * @param id L'identifiant du compte à supprimer (PathVariable).
     * @throws GeneralException
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCompte(@PathVariable Long id) throws GeneralException {
        compteService.deleteCompte(id);
        return new ResponseEntity<>("Compte Epargne with ID : " + id + " deleted !", HttpStatus.OK);
    }

//    @PutMapping("/virements")
//    public ResponseEntity<String> transferCashBetweenComptesEpargnes(@Valid @RequestBody CashTransferDTO cashTransferDTO)
//            throws GeneralException {
//
//        transferCashService.transferCashBetweenComptesEpargnes(cashTransferDTO);
//        return new ResponseEntity<>(
//                "Cash transfer successful for comptes épargnes ! \n" + cashTransferDTO.getMontantVirement() + " € transfered from Compte with id = "
//                        + cashTransferDTO.getIdCompteD() + " to Commpte with id = " + cashTransferDTO.getIdCompteC(),
//                HttpStatus.OK);
//    }
//
//    @PutMapping("/virements-vers-compte-courant")
//    public ResponseEntity<String> transferCashToCompteCourant(@Valid @RequestBody CashTransferDTO cashTransferDTO)
//            throws GeneralException {
//
//        transferCashService.transferCashToCompteCourant(cashTransferDTO);
//        return new ResponseEntity<>(
//                "Cash transfer successful for compte épargne to compte courant ! \n" + cashTransferDTO.getMontantVirement() + " € transfered from Compte épargne with id = "
//                        + cashTransferDTO.getIdCompteD() + " to Commpte courant with id = " + cashTransferDTO.getIdCompteC(),
//                HttpStatus.OK);
//    }

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
