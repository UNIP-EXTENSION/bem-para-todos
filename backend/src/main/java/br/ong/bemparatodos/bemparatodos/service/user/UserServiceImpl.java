package br.ong.bemparatodos.bemparatodos.service.user;

import br.ong.bemparatodos.bemparatodos.entity.user.User;
import br.ong.bemparatodos.bemparatodos.mapper.user.UserInsertMapper;
import br.ong.bemparatodos.bemparatodos.mapper.user.UserMapper;
import br.ong.bemparatodos.bemparatodos.record.user.UserInsertRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserUpdateRecord;
import br.ong.bemparatodos.bemparatodos.repository.user.RoleRepository;
import br.ong.bemparatodos.bemparatodos.repository.user.UserRepository;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceInvalidException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserInsertMapper userInsertMapper;

  private final RoleRepository repository;

  private static Set<UserRecord> userRecords;

  private final UserMapper userMapper;
  private final RoleRepository roleRepository;

  @Autowired
  public UserServiceImpl(
      final UserRepository userRepository,
      final UserInsertMapper userInsertMapper,
      final RoleRepository repository,
      final UserMapper userMapper,
      final RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.userInsertMapper = userInsertMapper;
    this.repository = repository;
    this.userMapper = userMapper;
    this.roleRepository = roleRepository;
  }

  @Override
  public Page<UserInsertRecord> findAllPaged(Pageable pageable) {
    final Page<User> userPage = userRepository.findAll(pageable);
    final Set<UserInsertRecord> userInsertRecords = userPage
        .getContent()
        .stream()
        .map(userInsertMapper::entitytoRecord)
        .collect(Collectors.toSet());

    return new PageImpl<>(
        new ArrayList<>(userInsertRecords), pageable, userPage.getTotalElements());
  }

  @Override
  public UserInsertRecord findById(UUID uuid) {
    return null;
  }

  @Override
  public UserInsertRecord save(@Valid final UserInsertRecord entity) {
    User user = userInsertMapper.recordToEntity(entity);
    
    if(user.getRoles() == null) {
    	user.setRoles(new HashSet<>());
    }

    user.getRoles()
        .forEach(role -> roleRepository.findByAuthority(role.getAuthority())
            .ifPresentOrElse(existingRole -> role.setId(existingRole.getId()), () -> {
              throw new ResourceInvalidException("Role was not exists");
            }));

    if (nonNull(entity.id()))
      throw new ResourceInvalidException("The provided resource has an ID and cannot be created.");

    final String password = new BCryptPasswordEncoder()
        .encode(user.getPassword());
    user.setPassword(password);

    user = userRepository.save(user);
    return userInsertMapper.entitytoRecord(user);
  }

  @Override
  public UserInsertRecord update(final UUID uuid, UserInsertRecord entity) {
    return null;
  }

  @Transactional
  @Override
  public Map<String, Object> updatePartially(final UUID uuid, final UserInsertRecord record) {

    String encodedPassword = null;
    if (record.password() != null) {
      encodedPassword = new BCryptPasswordEncoder().encode(record.password());
    }

    final int updatedRows = userRepository.updateUserPartially(
        uuid,
        record.firstName(),
        record.lastName(),
        record.email(),
        encodedPassword
    );

    if (updatedRows == 0) {
      throw new ResourceInvalidException("User not found");
    }

    final Map<String, Object> updatedFields = userInsertMapper.toUpdatedFieldsMap(record);
    if (nonNull(encodedPassword)) {
      updatedFields.put("password", encodedPassword);
    }
    return updatedFields;
  }

  @Override
  public void delete(final UUID uuid) {

  }
}
