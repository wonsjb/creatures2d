package net.barbux.creatures2d;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class WorldTest {

  @Test
  public void constructorInputNull1OutputNotNull() {
    final ArrayList<Creature> creatures = new ArrayList<Creature>();
    creatures.add(null);
    final World actual = new World(null, creatures);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    arrayList.add(null);
    Assert.assertEquals(arrayList, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }

  @Test
  public void constructorInputNull3OutputNotNull() {
    final ArrayList<Creature> creatures = new ArrayList<Creature>();
    creatures.add(null);
    creatures.add(null);
    creatures.add(null);
    final World actual = new World(null, creatures);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    Assert.assertEquals(arrayList, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }

  @Test
  public void constructorInputNullNotNullOutputNotNull() {
    final ArrayList<Creature> creatures = new ArrayList<Creature>();
    final World actual = new World(null, creatures);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Assert.assertEquals(arrayList, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }

  @Test
  public void constructorInputNullOutputNotNull() {
    final World actual = new World(null);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Assert.assertEquals(arrayList, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }
}
