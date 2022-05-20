package tech.jhipster.reactiveapp.dummy.application;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.reactiveapp.dummy.domain.DummyEntity;
import tech.jhipster.reactiveapp.dummy.domain.DummyService;
import tech.jhipster.reactiveapp.dummy.infrastructure.primary.dto.DummyDTO;

@Service
public class DummyApplicationService {

  private final DummyService dummyService;

  public DummyApplicationService(DummyService dummyService) {
    this.dummyService = dummyService;
  }

  public Flux<DummyDTO> list() {
    return dummyService.list().map(DummyDTO::toDummyDTO);
  }

  public Mono<DummyDTO> add(DummyDTO dummyDTO) {
    DummyEntity dummyEntityToAdd = DummyDTO.toDummy(dummyDTO);
    return dummyService.add(dummyEntityToAdd).map(DummyDTO::toDummyDTO);
  }

  public Mono<DummyDTO> incrementCount(String name) {
    return dummyService.incrementCount(name).map(DummyDTO::toDummyDTO);
  }

  public Mono<Void> clear() {
    return dummyService.clear();
  }
}
