package tech.jhipster.reactiveapp.dummy.infrastructure.secondary;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.reactiveapp.dummy.domain.DummyEntity;
import tech.jhipster.reactiveapp.dummy.domain.DummyRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DummyInMemoryRepository implements DummyRepository {

  private final Map<String, DummyEntity> dummys = new ConcurrentHashMap<>();

  @Override
  public Flux<DummyEntity> list() {
    return Flux.fromIterable(dummys.values().stream().toList());
  }

  @Override
  public Mono<DummyEntity> add(DummyEntity dummy) {
    dummys.put(dummy.name(), dummy);
    return Mono.just(dummy);
  }

  @Override
  public Mono<DummyEntity> update(DummyEntity dummy) {
    return add(dummy);
  }

  @Override
  public Mono<Void> clear() {
    dummys.clear();
    return Mono.empty();
  }
}
