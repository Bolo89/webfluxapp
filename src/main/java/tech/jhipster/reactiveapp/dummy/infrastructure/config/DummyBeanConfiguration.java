package tech.jhipster.reactiveapp.dummy.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.reactiveapp.dummy.domain.DummyDomainService;
import tech.jhipster.reactiveapp.dummy.domain.DummyRepository;
import tech.jhipster.reactiveapp.dummy.domain.DummyService;

@Configuration
public class DummyBeanConfiguration {

  private final DummyRepository dummyRepository;

  public DummyBeanConfiguration(DummyRepository dummyRepository) {
    this.dummyRepository = dummyRepository;
  }

  @Bean
  public DummyService dummyService() {
    return new DummyDomainService(dummyRepository);
  }
}
