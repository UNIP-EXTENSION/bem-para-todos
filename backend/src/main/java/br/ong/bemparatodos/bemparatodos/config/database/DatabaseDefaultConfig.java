package br.ong.bemparatodos.bemparatodos.config.database;

import br.com.kaliware.lib.database.boot.dbconfigtool.service.spring.AdapterDataSourceSpring;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

import static br.ong.bemparatodos.bemparatodos.config.database.DatabaseDefaultConfig.*;


@Configuration()
@EnableTransactionManagement
@EnableJpaRepositories(
   basePackages = {REPOSITORY_PACKAGE},
   transactionManagerRef = TRANSACTION_MANAGER,
   entityManagerFactoryRef = ENTITY_MANAGER)
public class DatabaseDefaultConfig extends AdapterDataSourceSpring {

  public static final String ENTITY_MANAGER = "defaultEntityManagerFactory";
  public static final String DATASOURCE = "defaultDatasource";
  public static final String TRANSACTION_MANAGER = "defaultTransactionManager";

  public static final String REPOSITORY_PACKAGE = "br.ong.bemparatodos.bemparatodos.repository";
  public static final String ENTITY_PACKAGE = "br.ong.bemparatodos.bemparatodos.entity";

  public DatabaseDefaultConfig(Environment environment) {
    super(environment);
  }

  @Override
  public String getConnectionPrefix() {
    return "default";
  }

  @Override
  public List<String> getPackageEntity() {
    return Collections.singletonList(ENTITY_PACKAGE);
  }

  @Override
  @Bean(DATASOURCE)
  public DataSource dataSource() {
    return super.dataSource();
  }

  @Override
  @Bean(ENTITY_MANAGER)
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier(DATASOURCE) DataSource dataSource) {
    return super.entityManagerFactory(dataSource);
  }

  @Override
  @Bean(TRANSACTION_MANAGER)
  public PlatformTransactionManager transactionManager(@Qualifier(ENTITY_MANAGER) EntityManagerFactory emf) {
    return super.transactionManager(emf);
  }



}