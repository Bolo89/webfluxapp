package tech.jhipster.reactiveapp.dummy.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DummyService {

  Flux<DummyEntity> list();

  Mono<DummyEntity> add(DummyEntity dummy);

  Mono<DummyEntity> incrementCount(String name);

  Mono<Void> clear();
}
