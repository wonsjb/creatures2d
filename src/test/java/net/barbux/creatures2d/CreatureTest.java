package net.barbux.creatures2d;

import net.barbux.creatures2d.Geometry.Vector;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class CreatureTest {

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Test
  public void addInputNotNullOutputTrue() {
    final Creature creature = new Creature();
    final Creature.NodeList creatureNodeList = creature.new NodeList();
    final Creature.Node node = new Creature.Node(2.0, 2.0);
    Assert.assertTrue(creatureNodeList.add(node));
  }

  @Test
  public void cloneOutputNotNull6() {
    final Creature creature = new Creature();
    final Creature actual = creature.clone();
    Assert.assertNotNull(actual);
    final ArrayList<Creature.Bone> arrayList = new ArrayList<Creature.Bone>();
    Assert.assertEquals(arrayList, actual.allBones);
    Assert.assertNotNull(actual.allNodes);
    Assert.assertNull(actual.physics);
    Assert.assertEquals(0.0, actual.maxReached, 0.0);
    final ArrayList<Creature.Muscle> arrayList1 = new ArrayList<Creature.Muscle>();
    Assert.assertEquals(arrayList1, actual.allMuscles);
    Assert.assertEquals(0, actual.creatureId);
  }

  @Test
  public void constructorOutputNotNull() {
    final Creature actual = new Creature();
    Assert.assertNotNull(actual);
    final ArrayList<Creature.Bone> arrayList = new ArrayList<Creature.Bone>();
    Assert.assertEquals(arrayList, actual.allBones);
    Assert.assertNotNull(actual.allNodes);
    Assert.assertNull(actual.physics);
    Assert.assertEquals(0.0, actual.maxReached, 0.0);
    final ArrayList<Creature.Muscle> arrayList1 = new ArrayList<Creature.Muscle>();
    Assert.assertEquals(arrayList1, actual.allMuscles);
    Assert.assertEquals(0, actual.creatureId);
  }

  @Test
  public void getCenterOutputNotNull() {
    final Creature creature = new Creature();
    final Vector actual = creature.getCenter();
    Assert.assertNotNull(actual);
    Assert.assertEquals(0x1.fffffffffffffp+1022 /* 8.98847e+307 */, actual.y, 0.0);
    Assert.assertEquals(0x1.fffffffffffffp+1022 /* 8.98847e+307 */, actual.x, 0.0);
  }

  @Test
  public void getFitnessOutputZero() {
    final Creature creature = new Creature();
    Assert.assertEquals(0.0, creature.getFitness(), 0.0);
  }

  @Test
  public void getInputPositiveOutputIndexOutOfBoundsException() {
    final Creature creature = new Creature();
    final Creature.NodeList creatureNodeList = creature.new NodeList();
    thrown.expect(IndexOutOfBoundsException.class);
    creatureNodeList.get(3);
  }

  @Test
  public void removeInputPositiveOutputIndexOutOfBoundsException() {
    final Creature creature = new Creature();
    final Creature.NodeList creatureNodeList = creature.new NodeList();
    thrown.expect(IndexOutOfBoundsException.class);
    creatureNodeList.remove(2);
  }

  @Test
  public void sizeOutputZero() {
    final Creature creature = new Creature();
    final Creature.NodeList creatureNodeList = creature.new NodeList();
    Assert.assertEquals(0, creatureNodeList.size());
  }

  @Test
  public void setExpectedLengthInputPositiveOutputVoid() {
    final Creature.Node creature$Node = new Creature.Node(0.5, 0.5);
    final Creature.Node creature$Node1 = new Creature.Node(0.5, 0.5);
    final Creature.Muscle thisObj = new Creature.Muscle(creature$Node, creature$Node1, 0.5, 0.5, 0.5);
    final double arg0 = 0.5;
    thisObj.setExpectedLength(arg0);
    Assert.assertEquals(-0x1.1a62633145c07p-55 /* -3.06162e-17 */, thisObj.expectedLength, 0.0);
  }
}
