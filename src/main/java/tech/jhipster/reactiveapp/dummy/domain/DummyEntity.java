package tech.jhipster.reactiveapp.dummy.domain;

import tech.jhipster.reactiveapp.error.domain.Assert;

import java.util.Objects;

public final class DummyEntity {
  private final String name;
  private int counter;

  public DummyEntity(String name, int counter) {
    Assert.notBlank("name", name);
    this.name = name;
    this.counter = counter;
  }

  public String name() {
    return name;
  }

  public int getCounter() {
    return counter;
  }

  public DummyEntity setCounter(int counter) {
    this.counter = counter;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (DummyEntity) obj;
    return Objects.equals(this.name, that.name) &&
      this.counter == that.counter;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, counter);
  }

  @Override
  public String toString() {
    return "DummyEntity[" +
      "name=" + name + ", " +
      "counter=" + counter + ']';
  }

}
