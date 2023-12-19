package br.com.projeto.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.template.dto.UserDto;
import br.com.projeto.template.service.UserService;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserDto> listAll() {
		return userService.listAll();
	}
	
	@PostMapping
	public List<UserDto> createUser(@RequestBody UserDto user) {
		userService.createUser(user);
		return userService.listAll();
	}
	
	@PutMapping
	public UserDto updateUser(@RequestBody UserDto user) {
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
	}
	
	@GetMapping("/{id}")
	public UserDto searchById(@PathVariable("id") Long id) {
		return userService.searchById(id);
	}
}
