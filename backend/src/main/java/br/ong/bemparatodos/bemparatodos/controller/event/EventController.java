package br.ong.bemparatodos.bemparatodos.controller.event;

import br.ong.bemparatodos.bemparatodos.controller.CrudController;
import br.ong.bemparatodos.bemparatodos.record.event.EventRecord;
import br.ong.bemparatodos.bemparatodos.service.CrudService;
import br.ong.bemparatodos.bemparatodos.service.event.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController extends CrudController<EventRecord, UUID> {

  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @Override
  protected CrudService<EventRecord, UUID> getService() {
    return eventService;
  }
}
