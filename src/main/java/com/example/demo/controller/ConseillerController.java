package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.mapper.ConseillerMapper;
import com.example.demo.model.Client;
import com.example.demo.model.Conseiller;
import com.example.demo.service.ClientService;
import com.example.demo.service.ConseillerService;

@RestController
@RequestMapping("/conseillers")
public class ConseillerController {

	private final ConseillerService conseillerService;
	private final ClientService clientService;

	@Autowired
	private ConseillerMapper mapper;

	public ConseillerController(ConseillerService conseillerService, ClientService clientService) {
		this.conseillerService = conseillerService;
		this.clientService = clientService;
	}

	// Get all Conseillers
	@GetMapping
	public ResponseEntity<List<ConseillerDTO>> getAllConseillers() {
		List<ConseillerDTO> conseillersDTO = conseillerService.getAllConseillers().stream().map(c -> mapper.ToDto(c))
				.collect(Collectors.toList());
		return new ResponseEntity<>(conseillersDTO, HttpStatus.OK);
	}

	// Get a Conseiller by ID
	@GetMapping("/{id}")
	public ResponseEntity<ConseillerDTO> getConseillerById(@PathVariable Long id) {
		try {
			Optional<Conseiller> conseillerOptional = conseillerService.getConseillerById(id);
			if (conseillerOptional.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			Conseiller conseiller = conseillerOptional.get();
			ConseillerDTO conseillerDTO = mapper.ToDto(conseiller);
			return new ResponseEntity<>(conseillerDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping
	public ResponseEntity<ConseillerDTO> createConseiller(@RequestBody ConseillerDTO conseillerDTO)
			throws GeneralException {
		Conseiller conseiller = mapper.toConseiller(conseillerDTO);
		ConseillerDTO createdConseiller = conseillerService.createConseiller(conseillerDTO);
		return new ResponseEntity<>(createdConseiller, HttpStatus.CREATED);
	}

	// Update a Conseiller by ID
	@PutMapping("/{id}")
	public ResponseEntity<ConseillerDTO> updateConseiller(@PathVariable Long id,
			@RequestBody ConseillerDTO conseillerDTO) throws GeneralException {
		ConseillerDTO updatedConseiller = conseillerService.updateConseiller(id, conseillerDTO);
		return new ResponseEntity<>(updatedConseiller, HttpStatus.OK);

	}

	// Delete a Conseiller by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteConseiller(@PathVariable Long id) throws GeneralException {
		Optional<Conseiller> ConseillerOptional = conseillerService.getConseillerById(id);
		if (ConseillerOptional.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		conseillerService.deleteConseiller(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/virement/comptes/courants")
	ResponseEntity<String> virementCompteCourantToCompteCourant(@RequestParam Long idEmetteur,
			@RequestParam Long idRecepteur, @RequestParam double montant) throws GeneralException {
		Optional<Client> optionalClientEmetteur = clientService.getClientById(idEmetteur);
		Optional<Client> optionalClientRecepteur = clientService.getClientById(idRecepteur);
		Client clientEmetteur = optionalClientEmetteur.get();
		Client clientRecepteur = optionalClientRecepteur.get();
		return new ResponseEntity<>(conseillerService.virementComptesCourants(montant, clientEmetteur, clientRecepteur),
				HttpStatus.OK);
	}

	@PutMapping("/virement")
	public ResponseEntity<String> virement(@RequestParam Long idEmetteur, @RequestParam double montant,
			@RequestParam String typeVirement) throws GeneralException {
		try {
			switch (typeVirement) {
			case "compteCourantVersCompteEpargne": {
				Optional<Client> client = clientService.getClientById(idEmetteur);
				if (client.isEmpty()) {
					throw new GeneralException("Client non trouvé");
				}
				return ResponseEntity.ok(conseillerService.virementCourantEpargne(montant, client.get()));
			}
			case "compteEpargneVersCompteCourant": {
				Optional<Client> client = clientService.getClientById(idEmetteur);
				if (client.isEmpty()) {
					throw new GeneralException("Client non trouvé");
				}
				return ResponseEntity.ok(conseillerService.virementEpargneCourant(montant, client.get()));
			}
			default:
				throw new IllegalArgumentException("Type de virement non pris en charge: " + typeVirement);
			}
		} catch (GeneralException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>("Une erreur est survenue", HttpStatus.INTERNAL_SERVER_ERROR);
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
