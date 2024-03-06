package br.ong.bemparatodos.bemparatodos.config.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI bemParaTodosAPI() {
    return new OpenAPI()
       .info(new Info()
          .title("Bem para todos - API")
          .description("A API `Bem Para Todos` é disponibilizada através de serviço `HTTP REST`.")
          .contact(new Contact()
             .name("Noah Lourenço")
             .email("noah.damian.tech@gmail.com")
             .url("https://github.com/Kaliware")
          ).contact(new Contact()
             .name("Pablo Junior")
             .email("pablojuniorgn2@gmail.com")
             .url("https://github.com/pablojg9")
          )
       );
  }
}