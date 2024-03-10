package br.ong.bemparatodos.bemparatodos.controller.event;

import br.ong.bemparatodos.bemparatodos.controller.CrudController;
import br.ong.bemparatodos.bemparatodos.record.event.DressCodeRecord;
import br.ong.bemparatodos.bemparatodos.service.CrudService;
import br.ong.bemparatodos.bemparatodos.service.event.dresscode.DressCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/dress-codes")
public class DressCodeController extends CrudController<DressCodeRecord, UUID> {

  private final DressCodeService service;

  @Autowired
  public DressCodeController(DressCodeService service) {
    this.service = service;
  }

  @Override
  protected CrudService<DressCodeRecord, UUID> getService() {
    return service;
  }

}
