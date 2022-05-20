package tech.jhipster.reactiveapp.dummy.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DummyRepository {

  Flux<DummyEntity> list();

  Mono<DummyEntity> add(DummyEntity dummy);

  Mono<DummyEntity> update(DummyEntity dummy);

  Mono<Void> clear();
}
