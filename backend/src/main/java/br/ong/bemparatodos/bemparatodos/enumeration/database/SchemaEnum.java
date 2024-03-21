package br.ong.bemparatodos.bemparatodos.enumeration.database;

public enum SchemaEnum {

  MAIN("main");

  private final String schemaName;

  SchemaEnum(String schemaName) {
    this.schemaName = schemaName;
  }

  public String getSchemaName() {
    return schemaName;
  }
}
