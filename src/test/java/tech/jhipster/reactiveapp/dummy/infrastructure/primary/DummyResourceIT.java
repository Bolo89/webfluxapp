package tech.jhipster.reactiveapp.dummy.infrastructure.primary;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import tech.jhipster.reactiveapp.IntegrationTest;
import tech.jhipster.reactiveapp.dummy.domain.DummyEntity;
import tech.jhipster.reactiveapp.dummy.domain.DummyRepository;
import tech.jhipster.reactiveapp.dummy.infrastructure.primary.dto.DummyDTO;

@IntegrationTest
@AutoConfigureWebTestClient
class DummyResourceIT {

  @Autowired
  WebTestClient webTestClient;

  @Autowired
  DummyRepository dummyRepository;

  @Test
  void shouldGetList() {
    dummyRepository.add(new DummyEntity("name", 0)).block();

    webTestClient
      .get()
      .uri("/api/dummy")
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody()
      .jsonPath("$").value(Matchers.hasSize(1))
      .jsonPath("$[0].name").isEqualTo("name")
      .jsonPath("$[0].counter").isEqualTo(0);
  }

  @Test
  void shouldAdd() {
    webTestClient
      .post()
      .uri("/api/dummy")
      .body(BodyInserters.fromValue(new DummyDTO("name", 3)))
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isCreated()
      .expectBody()
      .jsonPath("$.name").isEqualTo("name")
      .jsonPath("$.counter").isEqualTo(3);
  }

  @Test
  void shouldUpdate() {
    dummyRepository.add(new DummyEntity("name", 0)).block();

    webTestClient
      .put()
      .uri("/api/dummy/{name}", "name")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isOk()
      .expectBody()
      .jsonPath("$.name").isEqualTo("name")
      .jsonPath("$.counter").isEqualTo(1);
  }

  @Test
  void shouldClear() {
    dummyRepository.add(new DummyEntity("name", 0)).block();

    webTestClient
      .delete()
      .uri("/api/dummy")
      .accept(MediaType.APPLICATION_JSON)
      .exchange()
      .expectStatus()
      .isNoContent();
  }
}
