package com.example.quiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz.repository.User;
import com.example.quiz.service.UserService;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/quiz/user")
public class UserController {
	@Autowired
	UserService service;
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "Sucess";
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> login(@RequestBody User user)
	{
		System.out.println(user);
		return ResponseEntity.ok(service.login(user));
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<Map<String,String>> addUser(@RequestBody User user)
	{
		System.out.println(user);
		return ResponseEntity.ok(service.addUser(user));
	}

	@PutMapping("/updateUser")
	public ResponseEntity<Map<String,String>> updateUser(@RequestBody User user)
	{
		//System.out.println(user+" hello");
		service.updateUser(user);
		Map<String,String> response=new HashMap<>();
		response.put("status", "Saved Changes Sucessfully");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getUserData/{id}")
	public List<Map<String,Object>> getUserData(@PathVariable int id)
	{
		return service.getUserData(id);
	}

}
