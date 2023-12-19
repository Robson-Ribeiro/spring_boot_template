package br.com.projeto.template.dto;


import org.springframework.beans.BeanUtils;

import br.com.projeto.template.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserDto {

	private Long id;

	@NotBlank
	private String name;
	
	@NotBlank
	private String login;

	@NotBlank
	private String password;
	
	
	public UserDto(UserEntity entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public UserDto(String name, String login, String password) {
		this.name = name;
		this.login = login;
		this.password = password;
	}
}
