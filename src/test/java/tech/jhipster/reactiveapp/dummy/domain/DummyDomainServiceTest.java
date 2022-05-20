package tech.jhipster.reactiveapp.dummy.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.reactiveapp.UnitTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static reactor.core.publisher.Mono.when;

@UnitTest
@ExtendWith(MockitoExtension.class)
class DummyDomainServiceTest {
  @Mock
  DummyRepository dummyRepository;

  @InjectMocks
  DummyDomainService dummyDomainService;

  @Test
  void shouldList() {
    // Given
    DummyEntity dummyEntity = new DummyEntity("name", 0);
    when(dummyRepository.list()).thenReturn(Flux.fromIterable(List.of(dummyEntity)));

    // When
    Iterable<DummyEntity> dummyEntities = dummyDomainService.list().toIterable();

    // Then
    assertThat(dummyEntities).hasSize(1).containsExactly(dummyEntity);
  }

  @Test
  void shouldAdd() {
    // Given
    DummyEntity dummyEntityToAdd = new DummyEntity("name", 0);
    when(dummyRepository.add(dummyEntityToAdd)).thenReturn(Mono.just(dummyEntityToAdd));

    // When
    DummyEntity addedDummyEntity = dummyDomainService.add(dummyEntityToAdd).block();

    // Then
    assertThat(addedDummyEntity).isEqualTo(dummyEntityToAdd);
  }

  @Test
  void incrementCount() {
    // Given
    DummyEntity dummyEntityToUpdate = new DummyEntity("name", 0);
    when(dummyRepository.list()).thenReturn(Flux.fromIterable(List.of(dummyEntityToUpdate)));

    // When
    DummyEntity updatedDummyEntity = dummyDomainService.incrementCount("name").block();

    // Then
    assertThat(updatedDummyEntity).isEqualTo(dummyEntityToUpdate);
  }

  @Test
  void shouldClear() {
    // Given
    when(dummyRepository.clear()).thenReturn(Mono.empty());

    // When
    dummyDomainService.clear().block();

    // Then
    verify(dummyRepository).clear();
  }
}
