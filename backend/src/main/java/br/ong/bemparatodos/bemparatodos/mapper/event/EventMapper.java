package br.ong.bemparatodos.bemparatodos.mapper.event;

import br.ong.bemparatodos.bemparatodos.config.mapper.MapperConfiguration;
import br.ong.bemparatodos.bemparatodos.entity.event.Event;
import br.ong.bemparatodos.bemparatodos.entity.file.File;
import br.ong.bemparatodos.bemparatodos.record.event.EventRecord;
import br.ong.bemparatodos.bemparatodos.record.file.FileRecord;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.FileConvertException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Mapper(config = MapperConfiguration.class)
public interface EventMapper {

  @Mapping(target = "eventDetails", ignore = true)
  //@Mapping(target = "files", source = "files", qualifiedByName = "mapFilesToRecords")
  EventRecord entitytoDto(Event entity);

  //@Mapping(target = "files", source = "files", qualifiedByName = "mapRecordsToFiles")
  Event dtoToEntity(EventRecord dto);

  @Named("blobToByteArray")
  default byte[] blobToByteArray(Blob blob) {
    requireNonNull(blob);

    try {
      return blob.getBytes(1, (int) blob.length());
    } catch (SQLException e) {
      throw new FileConvertException("Erro ao converter para byte[]", e);
    }
  }

  @Named("byteArrayToBlob")
  default Blob byteArrayToBlob(byte[] data) {
    requireNonNull(data);

    try {
      return new SerialBlob(data);
    } catch (SQLException e) {
      throw new RuntimeException("Failed to convert byte[] to Blob", e);
    }
  }

 /* @Named("mapFilesToRecords")
  default Collection<FileRecord> mapFilesToRecords(Collection<File> files) {
    return files.stream()
        .map(file -> new FileRecord(
            file.getId(),
            file.getFileName(),
            file.getFileType(),
            file.getFileSize(),
            file.getDescription(),
            file.getUploadDate(),
            blobToByteArray(file.getData()),
            file.getUrlFile()

        ))
        .collect(Collectors.toList());
  }

  @Named("mapRecordsToFiles")
  default Collection<File> mapRecordsToFiles(Collection<FileRecord> fileRecords) {
    return fileRecords.stream()
        .map(record -> {
          File file = new File();
          file.setId(record.id());
          file.setFileName(record.fileName());
          file.setFileType(record.fileType());
          file.setFileSize(record.fileSize());
          file.setDescription(record.description());
          file.setData(byteArrayToBlob(record.data()));
          return file;
        })
        .collect(Collectors.toList());
  }*/

}
