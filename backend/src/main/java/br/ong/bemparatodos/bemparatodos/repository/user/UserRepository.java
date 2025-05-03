package br.ong.bemparatodos.bemparatodos.repository.user;


import br.ong.bemparatodos.bemparatodos.entity.user.Role;
import br.ong.bemparatodos.bemparatodos.entity.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  @Query(value = "select r.roles from User r where r.id = :id")
  Set<Role> findByRoles(UUID id);


  @Modifying
  @Query("UPDATE User u SET " +
      "u.firstName = COALESCE(:firstName, u.firstName), " +
      "u.lastName = COALESCE(:lastName, u.lastName), " +
      "u.email = COALESCE(:email, u.email), " +
      "u.password = COALESCE(:password, u.password) " +
      "WHERE u.id = :id")
  @Transactional
  int updateUserPartially(
      @Param("id") UUID id,
      @Param("firstName") String firstName,
      @Param("lastName") String lastName,
      @Param("email") String email,
      @Param("password") String password
  );

}