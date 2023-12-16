package br.com.projeto.template.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projeto.template.dto.UserDto;
import br.com.projeto.template.entity.UserEntity;
import br.com.projeto.template.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDto> listAll() {
		List<UserEntity> users = userRepository.findAll();
		return users.stream().map(UserDto::new).toList();
	}
	
	public void createUser(UserDto user) {
		UserEntity userEntity = new UserEntity(user);
		userRepository.save(userEntity);
	}
	
	public UserDto updateUser(UserDto user) {
		UserEntity userEntity = new UserEntity(user);
		return new UserDto(userRepository.save(userEntity));
	}
	
	public void deleteUser(Long id) {
		UserEntity userEntity = userRepository.findById(id).get();
		userRepository.delete(userEntity);
	}
	
	public UserDto searchById(Long id) {
		return new UserDto(userRepository.findById(id).get());
	}
}
