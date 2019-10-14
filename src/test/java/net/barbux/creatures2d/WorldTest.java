package net.barbux.creatures2d;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.diffblue.deeptestutils.Reflector;
import com.diffblue.deeptestutils.mock.DTUMemberMatcher;
import net.barbux.creatures2d.Creature.NodeList;
import net.barbux.creatures2d.Creature;
import net.barbux.creatures2d.Physics;
import net.barbux.creatures2d.World.CameraType;
import net.barbux.creatures2d.World;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.expectation.PowerMockitoStubber;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;

@RunWith(PowerMockRunner.class)
public class WorldTest {

  @Test
  public void cloneOutputNotNull() throws InvocationTargetException {
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    final World actual = world.clone();
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList1 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList1, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }

  @Test
  public void constructorInputNotNull1OutputNotNull() throws InvocationTargetException {
    final Supplier<Physics> physicsSupplier = (Supplier<Physics>) Reflector.getInstance("java.util.function.Supplier");
    final ArrayList<Creature> creatures = new ArrayList<Creature>();
    creatures.add(null);
    final World actual = new World(physicsSupplier, creatures);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    arrayList.add(null);
    Assert.assertEquals(arrayList, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNotNull(actual.physicsSupplier);
  }

  @Test
  public void constructorInputNotNull2OutputNotNull() throws InvocationTargetException {
    final Supplier<Physics> physicsSupplier = (Supplier<Physics>) Reflector.getInstance("java.util.function.Supplier");
    final ArrayList<Creature> creatures = new ArrayList<Creature>();
    creatures.add(null);
    creatures.add(null);
    final World actual = new World(physicsSupplier, creatures);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    arrayList.add(null);
    arrayList.add(null);
    Assert.assertEquals(arrayList, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNotNull(actual.physicsSupplier);
  }

  @Test
  public void constructorInputNull2OutputNotNull() throws InvocationTargetException {
    final ArrayList<Creature> creatures = new ArrayList<Creature>();
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    Reflector.setField(creature, "allNodes", null);
    creature.physics = null;
    creature.maxReached = 0x1.0000000001p+1 /* 2.0 */;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    creatures.add(creature);
    final Creature creature1 = new Creature();
    Reflector.setField(creature1, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", null);
    Reflector.setField(nodeList, "internal", null);
    Reflector.setField(creature1, "allNodes", nodeList);
    creature1.physics = null;
    creature1.maxReached = 2.0;
    Reflector.setField(creature1, "allMuscles", null);
    creature1.creatureId = 2;
    creatures.add(creature1);
    final World actual = new World(null, creatures);
    Assert.assertNotNull(actual);
    Assert.assertNotNull(actual.creatures);
    Assert.assertEquals(2, actual.creatures.size());
    Assert.assertNotNull(actual.creatures.get(0));
    Assert.assertNull(((Creature) actual.creatures.get(0)).allBones);
    Assert.assertNull(((Creature) actual.creatures.get(0)).allNodes);
    Assert.assertNull(((Creature) actual.creatures.get(0)).physics);
    Assert.assertEquals(0x1.0000000001p+1 /* 2.0 */, ((Creature) actual.creatures.get(0)).maxReached, 0.0);
    Assert.assertNull(((Creature) actual.creatures.get(0)).allMuscles);
    Assert.assertEquals(2, ((Creature) actual.creatures.get(0)).creatureId);
    Assert.assertNotNull(actual.creatures.get(1));
    Assert.assertNull(((Creature) actual.creatures.get(1)).allBones);
    Assert.assertNotNull(((Creature) actual.creatures.get(1)).allNodes);
    Assert.assertNull(((Creature) actual.creatures.get(1)).physics);
    Assert.assertEquals(2.0, ((Creature) actual.creatures.get(1)).maxReached, 0.0);
    Assert.assertNull(((Creature) actual.creatures.get(1)).allMuscles);
    Assert.assertEquals(2, ((Creature) actual.creatures.get(1)).creatureId);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }

  @PrepareForTest({Collections.class, ArrayList.class, World.class, Comparator.class})
  @Test
  public void getNextGenerationInputNotNullOutputNotNull() throws Exception, InvocationTargetException {

    PowerMockito.mockStatic(Comparator.class);
    PowerMockito.mockStatic(Collections.class);
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList arrayList = PowerMockito.mock(ArrayList.class);
    PowerMockito.when(arrayList.size()).thenReturn(1);
    Reflector.setField(arrayList, "modCount", 2);
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    final Random random = PowerMockito.mock(Random.class);
    final Comparator comparator = (Comparator) Reflector.getInstance("java.util.Comparator");
    PowerMockito.when(Collections.reverseOrder(or(isA(Comparator.class), isNull(Comparator.class)))).thenReturn(comparator);
    final Comparator comparator1 = (Comparator) Reflector.getInstance("java.util.Comparator");
    PowerMockito.when(Comparator.comparing(or(isA(Function.class), isNull(Function.class)))).thenReturn(comparator1);
    final World actual = world.getNextGeneration(random);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList1 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList1, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }

  @PrepareForTest({Collections.class, ArrayList.class, World.class, Comparator.class})
  @Test
  public void getNextGenerationInputNullOutputNotNull() throws Exception, InvocationTargetException {

    PowerMockito.mockStatic(Comparator.class);
    PowerMockito.mockStatic(Collections.class);
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList arrayList = PowerMockito.mock(ArrayList.class);
    PowerMockito.when(arrayList.size()).thenReturn(1);
    Reflector.setField(arrayList, "modCount", 2);
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    final Random random = null;
    final Comparator comparator = (Comparator) Reflector.getInstance("java.util.Comparator");
    PowerMockito.when(Collections.reverseOrder(or(isA(Comparator.class), isNull(Comparator.class)))).thenReturn(comparator);
    final Comparator comparator1 = (Comparator) Reflector.getInstance("java.util.Comparator");
    PowerMockito.when(Comparator.comparing(or(isA(Function.class), isNull(Function.class)))).thenReturn(comparator1);
    final World actual = world.getNextGeneration(random);
    Assert.assertNotNull(actual);
    final ArrayList<Creature> arrayList1 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList1, actual.creatures);
    Assert.assertEquals(0L, actual.lastUpdateNanos);
    Assert.assertNull(actual.physicsSupplier);
  }

  @Test
  public void initializePhysicsOutputVoid() throws InvocationTargetException {
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    world.initializePhysics();

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void updateInputPositiveOutputVoid() throws InvocationTargetException {
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    world.update(2L);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void updateInputPositiveOutputVoid1() throws InvocationTargetException {
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 1024L;
    Reflector.setField(world, "physicsSupplier", null);
    world.update(1024L);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void staticInitOutputVoid() throws InvocationTargetException {
    final Object constructed = Reflector.getInstance("net.barbux.creatures2d.World$CameraType");

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void staticInitOutputVoid1() throws InvocationTargetException {
    final Object constructed = Reflector.getInstance("net.barbux.creatures2d.World$1");

    // The method returns void, testing that no exception is thrown
  }
}
