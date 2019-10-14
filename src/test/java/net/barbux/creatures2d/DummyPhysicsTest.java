package net.barbux.creatures2d;

import net.barbux.creatures2d.Creature;
import net.barbux.creatures2d.DummyPhysics;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DummyPhysicsTest {

  @Test
  public void constructorOutputNotNull() {
    final DummyPhysics actual = new DummyPhysics();
    Assert.assertNotNull(actual);
  }

  @Test
  public void initializeInputNotNullOutputVoid() {
    final DummyPhysics dummyPhysics = new DummyPhysics();
    final Creature creature = new Creature();
    dummyPhysics.initialize(creature);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void updateInputPositiveOutputVoid() {
    final DummyPhysics dummyPhysics = new DummyPhysics();
    dummyPhysics.update(2L);

    // The method returns void, testing that no exception is thrown
  }
}
