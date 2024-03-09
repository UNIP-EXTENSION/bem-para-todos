package br.ong.bemparatodos.bemparatodos.repository;


import br.ong.bemparatodos.bemparatodos.entity.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {

}