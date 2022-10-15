package org.medicare.controller;

import org.medicare.entity.AuthenticationStatus;
import org.medicare.entity.User;
import org.medicare.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
private UserService userService;
 
public UserController(UserService userService) {
	super();
	this.userService = userService;
}
@PostMapping("/users")
public ResponseEntity insert(@RequestBody User user) {
	userService.insertUser(user);
	return new ResponseEntity<User>(HttpStatus.CREATED);
}

@PostMapping("/user")
public ResponseEntity<AuthenticationStatus> authenticate(@RequestBody User user) {
	System.out.println(user.getUsername()+"\t"+user.getPassword());
	AuthenticationStatus status=userService.getStatus(user.getUsername(), user.getPassword());
	return new ResponseEntity<AuthenticationStatus>(status,HttpStatus.ACCEPTED);
	
}
}
