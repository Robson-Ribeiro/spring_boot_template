package br.com.projeto.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projeto.template.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
