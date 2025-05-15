package br.ong.bemparatodos.bemparatodos.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID> {
  default Page<T> findAllPaged(Pageable pageable){return null;};

  default T findById(ID id){return null;};

  default T save(@Valid T entity){return null;};

  default T update(ID id, @Valid T entity){return null;};

  default void delete(ID id){};

}