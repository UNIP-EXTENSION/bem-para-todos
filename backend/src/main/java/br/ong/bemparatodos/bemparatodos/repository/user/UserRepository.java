package br.ong.bemparatodos.bemparatodos.repository.user;


import br.ong.bemparatodos.bemparatodos.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}