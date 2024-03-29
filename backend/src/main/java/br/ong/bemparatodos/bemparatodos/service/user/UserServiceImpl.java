package br.ong.bemparatodos.bemparatodos.service.user;

import br.ong.bemparatodos.bemparatodos.entity.user.Role;
import br.ong.bemparatodos.bemparatodos.entity.user.User;
import br.ong.bemparatodos.bemparatodos.repository.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("user is not found " + username));


    return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            grantedAuthorityRoles(user.getRoles())); // todo - dar outro nome para a entidade User, pois acaba dando conflito.
  }

  private Set<GrantedAuthority> grantedAuthorityRoles(Set<Role> roles) {
      return roles.stream()
              .map(x -> new SimpleGrantedAuthority(x.getAuthority()))
              .collect(Collectors.toSet());
  }
}
