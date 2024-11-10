package br.ong.bemparatodos.bemparatodos.service.event;

import br.ong.bemparatodos.bemparatodos.entity.event.DressCode;
import br.ong.bemparatodos.bemparatodos.entity.event.Event;
import br.ong.bemparatodos.bemparatodos.mapper.event.EventMapper;
import br.ong.bemparatodos.bemparatodos.record.event.EventRecord;
import br.ong.bemparatodos.bemparatodos.repository.event.EventRepository;
import br.ong.bemparatodos.bemparatodos.service.exception.database.DatabaseIntegrityViolationException;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceInvalidException;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.ong.bemparatodos.bemparatodos.util.page.PageUtils.convertCollectionToPage;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  private final EventMapper eventMapper;

  private static Set<EventRecord> events;

  public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
  }

  private void refresh() {
    events = eventRepository
        .findAll()
        .stream()
        .map(eventMapper::entitytoDto)
        .collect(Collectors.toSet());
  }

  private void checkAndSaveToMemory() {
    if (isNull(eventRepository) || events.isEmpty()) {
      events = eventRepository
          .findAll()
          .stream()
          .map(eventMapper::entitytoDto)
          .collect(Collectors.toSet());
    }
  }

  @Override
  public Page<EventRecord> findAllPaged(Pageable pageable) {
    checkAndSaveToMemory();
    if (events.isEmpty())
      throw new ResourceNotFoundException("No dress codes found.");
    return convertCollectionToPage(events, pageable);
  }

  @Override
  public EventRecord findById(UUID uuid) {
    checkAndSaveToMemory();
    return events
        .stream()
        .filter(d -> d.id().equals(uuid))
        .findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Dress code not found with id: " + uuid));
  }

  @Override
  public EventRecord save(EventRecord record) {
    try {
      if (nonNull(record.id()))
        throw new ResourceInvalidException("The provided resource has an ID and cannot be created.");

      Event entity = eventMapper.dtoToEntity(record);
      entity = eventRepository.save(entity);

      refresh();
      return eventMapper.entitytoDto(entity);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseIntegrityViolationException("Integrity violation");
    }
  }

  @Override
  public EventRecord update(UUID id, EventRecord record) {
    if (!eventRepository.existsById(id))
      throw new ResourceNotFoundException("Dress code not found with id: " + id);

    Event entity = eventMapper.dtoToEntity(record);
    entity.setId(id);
    entity = eventRepository.save(entity);

    refresh();
    return eventMapper.entitytoDto(entity);
  }

  @Override
  public void delete(UUID id) {
    try {
      if (!eventRepository.existsById(id))
        throw new ResourceNotFoundException("Dress code not found for deletion with ID: " + id);

      eventRepository.deleteById(id);
      refresh();
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseIntegrityViolationException("Integrity violation");
    }

  }
}
