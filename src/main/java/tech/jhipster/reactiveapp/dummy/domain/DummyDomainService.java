package tech.jhipster.reactiveapp.dummy.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DummyDomainService implements DummyService {

  private final DummyRepository dummyRepository;

  public DummyDomainService(DummyRepository dummyRepository) {
    this.dummyRepository = dummyRepository;
  }

  @Override
  public Flux<DummyEntity> list() {
    return dummyRepository.list();
  }

  @Override
  public Mono<DummyEntity> add(DummyEntity dummy) {
    return dummyRepository.add(dummy);
  }

  @Override
  public Mono<DummyEntity> incrementCount(String name) {
    return dummyRepository.list()
      .filter(d -> name.equalsIgnoreCase(d.name()))
      .map(d -> d.setCounter(d.getCounter() + 1))
      .flatMap(dummyRepository::update)
      .singleOrEmpty();
  }

  @Override
  public Mono<Void> clear() {
    return dummyRepository.clear();
  }
}
