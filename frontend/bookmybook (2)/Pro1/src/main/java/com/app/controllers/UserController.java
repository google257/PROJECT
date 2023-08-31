package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginRequestDto;
import com.app.dto.SignUpDato;
import com.app.dto.UserRequestDto;
import com.app.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
//	
//	@Autowired
//	private ProductService productServ;
	@Autowired
	private UserService userService;
	
	@PostMapping("/Login")
	public ResponseEntity<?> getById(@RequestBody LoginRequestDto dto){
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(dto.getEmail(), dto.getPassword()));
	}
	
	@PostMapping("/SignUp")
	public ResponseEntity<?> addUser(@RequestBody SignUpDato u){
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(u));
		
	}
	
//	@GetMapping("/Products")
//	public ResponseEntity<?> getAllProducts(){
//		
//		return ResponseEntity.status(HttpStatus.OK).body(productServ.getAllProducts());
//		
//	}
//	
	
	@GetMapping("/Roles")
	public ResponseEntity<?> getAdminUsers(){
		return ResponseEntity.status(HttpStatus.OK).body(userService.findByRole());
	}
	
	@PutMapping("/UpdateUser")
	public ResponseEntity<?> updateUser(@RequestBody UserRequestDto dto){
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(dto));
	}
	
	
}
