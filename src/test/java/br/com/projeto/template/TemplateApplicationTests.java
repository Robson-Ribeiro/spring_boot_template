package br.com.projeto.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.projeto.template.dto.UserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TemplateApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void createUserSuccess() {
		UserDto user = new UserDto("Rob", "login", "password");
		webTestClient
			.post()
			.uri("/user")
			.bodyValue(user)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].name").isEqualTo(user.getName())
			.jsonPath("$[0].login").isEqualTo(user.getLogin())
			.jsonPath("$[0].password").isEqualTo(user.getPassword());
	
	}
	
	@Test
	void createUserFailure() {
		
	}

}
