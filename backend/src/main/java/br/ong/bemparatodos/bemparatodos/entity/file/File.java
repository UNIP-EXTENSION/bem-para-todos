package br.ong.bemparatodos.bemparatodos.entity.file;

import jakarta.persistence.*;

import java.sql.Blob;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_file")
public class File {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "file_name", nullable = false)
  private String fileName;

  @Column(name = "file_type", length = 50, nullable = false)
  private String fileType;

  @Column(name = "file_size", nullable = false)
  private Long fileSize;

  @Column(name = "description")
  private String description;

  @Lob
  @Column(name = "data")
  private Blob data;

  @Column(name = "url")
  private String urlFile;

  @Column(name = "upload_date", nullable = false)
  private Instant uploadDate;

  public File() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public void setFileSize(Long fileSize) {
    this.fileSize = fileSize;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Blob getData() {
    return data;
  }

  public void setData(Blob data) {
    this.data = data;
  }

  public Instant getUploadDate() {
    return uploadDate;
  }

  public String getUrlFile() {
    return urlFile;
  }

  public void setUrlFile(String urlFile) {
    this.urlFile = urlFile;
  }

  public void setUploadDate(Instant uploadDate) {
    this.uploadDate = uploadDate;
  }

  @PrePersist
  public void prePersist() {
    uploadDate = Instant.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof File file)) return false;
    return Objects.equals(id, file.id) &&
       Objects.equals(fileName, file.fileName) &&
       Objects.equals(fileType, file.fileType) &&
       Objects.equals(fileSize, file.fileSize) &&
       Objects.equals(description, file.description) &&
       Objects.equals(data, file.data) &&
       Objects.equals(uploadDate, file.uploadDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fileName, fileType, fileSize, description, data, uploadDate);
  }
}
