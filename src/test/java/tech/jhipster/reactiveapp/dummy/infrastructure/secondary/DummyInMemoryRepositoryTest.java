package tech.jhipster.reactiveapp.dummy.infrastructure.secondary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.reactiveapp.UnitTest;
import tech.jhipster.reactiveapp.dummy.domain.DummyEntity;

import static org.assertj.core.api.Assertions.assertThat;

@UnitTest
@ExtendWith(MockitoExtension.class)
class DummyInMemoryRepositoryTest {

  @InjectMocks
  DummyInMemoryRepository dummyInMemoryRepository;

  @Test
  void shouldList() {
    // Given
    DummyEntity dummyEntity = new DummyEntity("name", 0);
    dummyInMemoryRepository.add(dummyEntity).block();

    // When
    Iterable<DummyEntity> dummyEntities = dummyInMemoryRepository.list().toIterable();

    // Then
    assertThat(dummyEntities).hasSize(1).containsExactly(dummyEntity);
  }

  @Test
  void shouldAdd() {
    // Given
    DummyEntity dummyEntityToAdd = new DummyEntity("name", 0);

    // When
    DummyEntity addedDummyEntity = dummyInMemoryRepository.add(dummyEntityToAdd).block();

    // Then
    assertThat(addedDummyEntity).isEqualTo(dummyEntityToAdd);
    assertThat(dummyInMemoryRepository.list().toIterable()).containsExactly(dummyEntityToAdd);
  }

  @Test
  void shouldUpdate() {
    // Given
    DummyEntity dummyEntityToUpdate = new DummyEntity("name", 2);

    // When
    DummyEntity updatedDummyEntity = dummyInMemoryRepository.update(dummyEntityToUpdate).block();

    // Then
    assertThat(updatedDummyEntity).isEqualTo(dummyEntityToUpdate);
    assertThat(dummyInMemoryRepository.list().toIterable()).containsExactly(dummyEntityToUpdate);
  }

  @Test
  void shouldClear() {
    // Given
    dummyInMemoryRepository.add(new DummyEntity("name", 0)).block();

    // When
    dummyInMemoryRepository.clear().subscribe();

    // Then
    assertThat(dummyInMemoryRepository.list().toIterable()).isEmpty();
  }
}
