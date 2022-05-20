package tech.jhipster.reactiveapp.dummy.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import tech.jhipster.reactiveapp.IntegrationTest;
import tech.jhipster.reactiveapp.dummy.domain.DummyEntity;
import tech.jhipster.reactiveapp.dummy.domain.DummyRepository;
import tech.jhipster.reactiveapp.dummy.infrastructure.primary.dto.DummyDTO;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class DummyApplicationServiceIT {

  @Autowired
  DummyApplicationService dummyApplicationService;

  @Autowired
  DummyRepository dummyRepository;

  @Test
  void shouldGetList() {
    // Given
    dummyRepository.add(new DummyEntity("name", 0)).block();

    // When
    Flux<DummyDTO> dummyDTOFlux = dummyApplicationService.list();

    //
    StepVerifier.create(dummyDTOFlux)
      .consumeNextWith(dummyDTO -> assertThat(dummyDTO)
        .extracting(DummyDTO::getName, DummyDTO::getCounter)
        .containsExactly("name", 0)
      ).verifyComplete();
  }

  @Test
  void shouldAdd() {
    // When
    dummyRepository.add(new DummyEntity("name", 0)).block();

    // Then
    Flux<DummyDTO> dummyDTOFlux = dummyApplicationService.list();
    StepVerifier.create(dummyDTOFlux)
      .consumeNextWith(dummyDTO -> assertThat(dummyDTO)
        .extracting(DummyDTO::getName, DummyDTO::getCounter)
        .containsExactly("name", 0)
      ).verifyComplete();
  }

  @Test
  void shouldIncrementCount() {
    // Given
    dummyRepository.add(new DummyEntity("name", 0)).block();

    // When
    Flux<DummyDTO> dummyDTOFlux = dummyApplicationService.list();
    Mono<DummyDTO> dummyDTOMono = dummyApplicationService.incrementCount("name");

    // Then
    StepVerifier.create(dummyDTOMono)
      .consumeNextWith(dummyDTO -> {
        assertThat(dummyDTO.getName()).isEqualTo("name");
        assertThat(dummyDTO.getCounter()).isEqualTo(1);
      })
      .verifyComplete();

    StepVerifier.create(dummyDTOFlux)
      .consumeNextWith(dummyDTO -> assertThat(dummyDTO)
        .extracting(DummyDTO::getName, DummyDTO::getCounter)
        .containsExactly("name", 1)
      ).verifyComplete();
  }

  @Test
  void shouldClear() {
    // Given
    dummyRepository.add(new DummyEntity("name", 0)).block();

    // When
    Mono<Void> voidMono = dummyApplicationService.clear();
    Flux<DummyDTO> dummyDTOFlux = dummyApplicationService.list();

    // Then
    StepVerifier.create(voidMono)
      .expectNextCount(0)
      .verifyComplete();

    StepVerifier.create(dummyDTOFlux)
      .verifyComplete();
  }
}
