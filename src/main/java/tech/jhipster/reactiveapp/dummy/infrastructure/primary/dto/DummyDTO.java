package tech.jhipster.reactiveapp.dummy.infrastructure.primary.dto;

import tech.jhipster.reactiveapp.dummy.domain.DummyEntity;

import java.util.Objects;

public final class DummyDTO {
  private String name;
  private int counter = 0;

  public DummyDTO() {}

  public DummyDTO(String name, int counter) {
    this.name = name;
    this.counter = counter;
  }

  public String getName() {
    return name;
  }

  public DummyDTO setName(String name) {
    this.name = name;
    return this;
  }

  public int getCounter() {
    return counter;
  }

  public DummyDTO setCounter(int counter) {
    this.counter = counter;
    return this;
  }

  public static DummyEntity toDummy(DummyDTO dummyDTO) {
    return dummyDTO != null ? new DummyEntity(dummyDTO.name, dummyDTO.counter) : null;
  }

  public static DummyDTO toDummyDTO(DummyEntity dummy) {
    return dummy != null ? new DummyDTO(dummy.name(), dummy.getCounter()) : null;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (DummyDTO) obj;
    return Objects.equals(this.name, that.name) &&
      this.counter == that.counter;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, counter);
  }

  @Override
  public String toString() {
    return "DummyDTO[" +
      "name=" + name + ", " +
      "counter=" + counter + ']';
  }

}
