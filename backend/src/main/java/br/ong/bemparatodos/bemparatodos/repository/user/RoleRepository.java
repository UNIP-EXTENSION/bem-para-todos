package br.ong.bemparatodos.bemparatodos.repository.user;


import br.ong.bemparatodos.bemparatodos.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

}