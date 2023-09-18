package com.example.demo.controller;

import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.dto.CompteCourantDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.CompteCourant;
import com.example.demo.service.CompteCourantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/comptes/courants")
public class CompteCourantController {

    /**
     * Service pour gérer les opérations sur les comptes.
     */
    @Autowired
    private CompteCourantService compteService;

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
    public ResponseEntity<List<CompteCourantDTO>> getAllComptesCourants() {
        List<CompteCourantDTO> compteCourants = compteService.getAllCompte();
        if (compteCourants.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(compteCourants, HttpStatus.OK);
        }
    }

    /**
     * Crée un nouveau compte avec une carte et l'associe à un client.
     *
     * @param compte Le compte à créer (RequestBody).
     * @param carte La carte à créer et associer (RequestBody).
     * @param clientId Le client à associer au compte (RequestParam).
     * @return Le compte créé.
     */
    @PostMapping
    ResponseEntity<CompteCourantDTO> createCompteWithClientAndCarte(@Valid @RequestBody CompteCourantDTO createCompteCourantDTO) throws GeneralException {
        CompteCourantDTO createdCompteCourant = compteService.createCompteWithClientAndCarte(createCompteCourantDTO);

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
    CompteCourantDTO getCompteById(@PathVariable Long id) throws GeneralException {
        return compteService.getCompteById(id);
    }
    
    @GetMapping("/NumCompte")
    Optional<CompteCourantDTO> getCompteByAccountNumber(@RequestParam String AccountNumber) throws GeneralException {
        return compteService.getCompteByAccountNumber(AccountNumber);
    }
    
    @GetMapping("/client/{clientId}")
    Optional<CompteCourantDTO> getCompteByClientId(@PathVariable Long clientId) throws GeneralException {
        return compteService.getCompteByClientId(clientId);
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
    ResponseEntity<CompteCourantDTO> updateCompte(@PathVariable Long id, @RequestBody CompteCourantDTO compte) throws GeneralException {
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
        return new ResponseEntity<>("Compte Courant with ID : " + id + " deleted !", HttpStatus.OK);
    }

//    @PutMapping("/virements")
//    public ResponseEntity<String> transferCashBetweenComptesCourants(@Valid @RequestBody CashTransferDTO cashTransferDTO)
//            throws GeneralException {
//        transferCashService.transferCashBetweenComptesCourants(cashTransferDTO);
//        return new ResponseEntity<>(
//                "Cash transfer successful for comptes courants ! \n" + cashTransferDTO.getMontantVirement() + " € transfered from Compte with id = "
//                        + cashTransferDTO.getIdCompteD() + " to Commpte with id = " + cashTransferDTO.getIdCompteC(),
//                HttpStatus.OK);
//    }
//
//    @PutMapping("/virements-vers-compte-epargne")
//    public ResponseEntity<String> transferCashToCompteEpargne(@Valid @RequestBody CashTransferDTO cashTransferDTO)
//            throws GeneralException {
//
//        transferCashService.transferCashToCompteEpargne(cashTransferDTO);
//        return new ResponseEntity<>(
//                "Cash transfer successful for compte courant to compte épargne ! \n" + cashTransferDTO.getMontantVirement() + " € transfered from Compte courant with id = "
//                        + cashTransferDTO.getIdCompteD() + " to Commpte épargne with id = " + cashTransferDTO.getIdCompteC(),
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
