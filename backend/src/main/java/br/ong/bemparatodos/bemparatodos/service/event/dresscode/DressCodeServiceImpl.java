package br.ong.bemparatodos.bemparatodos.service.event.dresscode;

import br.ong.bemparatodos.bemparatodos.entity.event.DressCode;
import br.ong.bemparatodos.bemparatodos.mapper.event.DressCodeMapper;
import br.ong.bemparatodos.bemparatodos.record.event.DressCodeRecord;
import br.ong.bemparatodos.bemparatodos.repository.event.DressCodeRepository;
import br.ong.bemparatodos.bemparatodos.service.exception.database.DatabaseIntegrityViolationException;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceInvalidException;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.ong.bemparatodos.bemparatodos.util.page.PageUtils.convertCollectionToPage;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class DressCodeServiceImpl implements DressCodeService {

  private final DressCodeRepository repository;

  private final DressCodeMapper mapper;

  private static Set<DressCodeRecord> dressCodes;

  @Autowired
  public DressCodeServiceImpl(DressCodeRepository repository, DressCodeMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  private void checkAndSaveToMemory() {
    if (isNull(dressCodes) || dressCodes.isEmpty()) {
      dressCodes = repository
         .findAll()
         .stream()
         .map(mapper::entitytoDto)
         .collect(Collectors.toSet());
    }
  }


  private void refresh() {
    dressCodes = repository
       .findAll()
       .stream()
       .map(mapper::entitytoDto)
       .collect(Collectors.toSet());
  }

  @Override
  @Transactional
  public Page<DressCodeRecord> findAllPaged(Pageable pageable) {
    checkAndSaveToMemory();
    if (dressCodes.isEmpty())
      throw new ResourceNotFoundException("No dress codes found.");
    return convertCollectionToPage(dressCodes, pageable);
  }

  @Override
  @Transactional
  public DressCodeRecord findById(UUID id) {
    checkAndSaveToMemory();
    return dressCodes
       .stream()
       .filter(d -> d.id().equals(id))
       .findFirst()
       .orElseThrow(() -> new ResourceNotFoundException("Dress code not found with id: " + id));
  }

  @Override
  @Transactional
  public DressCodeRecord save(@Valid DressCodeRecord record) {
    try {
      if (nonNull(record.id()))
        throw new ResourceInvalidException("The provided resource has an ID and cannot be created.");

      DressCode entity = mapper.dtoToEntity(record);
      entity = repository.save(entity);

      refresh();
      return mapper.entitytoDto(entity);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseIntegrityViolationException("Integrity violation");
    }
  }

  @Override
  @Transactional
  public DressCodeRecord update(UUID id, @Valid DressCodeRecord record) {
    if (!repository.existsById(id))
      throw new ResourceNotFoundException("Dress code not found with id: " + id);

    DressCode entity = mapper.dtoToEntity(record);
    entity.setId(id);
    entity = repository.save(entity);

    refresh();
    return mapper.entitytoDto(entity);
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    try {
      if (!repository.existsById(id))
        throw new ResourceNotFoundException("Dress code not found for deletion with ID: " + id);

      repository.deleteById(id);
      refresh();
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseIntegrityViolationException("Integrity violation");
    }

  }

}
