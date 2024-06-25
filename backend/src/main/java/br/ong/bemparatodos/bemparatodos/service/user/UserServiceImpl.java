package br.ong.bemparatodos.bemparatodos.service.user;

import br.ong.bemparatodos.bemparatodos.entity.user.User;
import br.ong.bemparatodos.bemparatodos.mapper.user.UserInsertMapper;
import br.ong.bemparatodos.bemparatodos.mapper.user.UserMapper;
import br.ong.bemparatodos.bemparatodos.record.event.DressCodeRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserInsertRecord;
import br.ong.bemparatodos.bemparatodos.record.user.UserRecord;
import br.ong.bemparatodos.bemparatodos.repository.user.UserRepository;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceInvalidException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserInsertMapper userInsertMapper;

  private static Set<UserRecord> userRecords;

  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(final UserRepository userRepository, final UserInsertMapper userInsertMapper, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userInsertMapper = userInsertMapper;
    this.userMapper = userMapper;
  }

  @Override
  public Page<UserInsertRecord> findAllPaged(Pageable pageable) {
    Page<User> userPage = userRepository.findAll(pageable);
    Set<UserInsertRecord>userInsertRecords =userPage.getContent().stream().map(userInsertMapper::entitytoRecord).collect(Collectors.toSet());
    return new PageImpl<>(new ArrayList<>(userInsertRecords), pageable, userPage.getTotalElements());
  }

  @Override
  public UserInsertRecord findById(UUID uuid) {
    return null;
  }

  @Override
  public UserInsertRecord save(@Valid UserInsertRecord entity) {
    User user = userInsertMapper.recordToEntity(entity);
    if (nonNull(entity.id())) {
      throw new ResourceInvalidException("The provided resource has an ID and cannot be created.");
    }

    user = userRepository.save(user);
    return userInsertMapper.entitytoRecord(user);
  }

  @Override
  public UserInsertRecord update(UUID uuid, UserInsertRecord entity) {
    return null;
  }

  @Override
  public void delete(UUID uuid) {

  }
}
