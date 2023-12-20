package br.com.projeto.template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.projeto.template.dto.UserDto;
import br.com.projeto.template.entity.UserEntity;
import br.com.projeto.template.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
class TemplateApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired
	private UserRepository userRepository;

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
		webTestClient
			.post()
			.uri("/user")
			.bodyValue(new UserDto("", "", "password2"))
			.exchange().expectStatus().isBadRequest();
	}

	@Test
	void listAllSuccess() {
		UserEntity user = new UserEntity("Rob", "login", "password");
		userRepository.save(user);
		webTestClient
			.get()
			.uri("/user")
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].name").isEqualTo(user.getName())
			.jsonPath("$[0].login").isEqualTo(user.getLogin())
			.jsonPath("$[0].password").isEqualTo(user.getPassword());
	}
	
	
	
	
}
