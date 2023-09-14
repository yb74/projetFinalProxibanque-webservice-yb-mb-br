package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ConseillerDTO;
import com.example.demo.exception.GeneralException;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam("username") String username, @RequestParam("password") String password) throws GeneralException{
            return new ResponseEntity<>(userService.login(username, password), HttpStatus.OK);
    }

    @GetMapping("/getinfo")
    public UserModel getInfoForLogeed(@RequestParam("username") String username, @RequestParam("password") String password) throws GeneralException{
        return userService.getInfoForLogged(username, password);
    }
    
    @GetMapping("/getConseiller")
    public ConseillerDTO getConseillerByUser(@RequestParam("username") String username, @RequestParam("password") String password) throws GeneralException{
        return userService.getConseillerAssociatedToUser(username, password);
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
