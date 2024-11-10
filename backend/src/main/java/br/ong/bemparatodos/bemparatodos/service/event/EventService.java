package br.ong.bemparatodos.bemparatodos.service.event;

import br.ong.bemparatodos.bemparatodos.record.event.EventRecord;
import br.ong.bemparatodos.bemparatodos.service.CrudService;

import java.util.UUID;

public interface EventService extends CrudService<EventRecord, UUID> {
}
