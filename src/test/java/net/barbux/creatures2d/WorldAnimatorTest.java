package net.barbux.creatures2d;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;

import com.diffblue.deeptestutils.Reflector;
import net.barbux.creatures2d.Creature;
import net.barbux.creatures2d.World;
import net.barbux.creatures2d.WorldAnimator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.Supplier;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

@RunWith(PowerMockRunner.class)
public class WorldAnimatorTest {

  @PrepareForTest(fullyQualifiedNames = {"net.barbux.creatures2d.WorldAnimator$1"}, value = WorldAnimator.class)
  @Test
  public void constructorInputNotNullNotNullOutputVoid() throws Exception, InvocationTargetException {
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList arrayList = new ArrayList();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    final Canvas canvas = PowerMockito.mock(Canvas.class);
    final AnimationTimer my1 = (AnimationTimer) PowerMockito.mock(Class.forName("net.barbux.creatures2d.WorldAnimator$1"));
    final WorldAnimator worldAnimator = (WorldAnimator) Reflector.getInstance("net.barbux.creatures2d.WorldAnimator");
    worldAnimator.speed = 1.0;
    worldAnimator.animationTimer = null;
    final World world1 = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList arrayList1 = new ArrayList();
    Reflector.setField(world1, "creatures", arrayList1);
    world1.lastUpdateNanos = 0L;
    Reflector.setField(world1, "physicsSupplier", null);
    worldAnimator.animatedWorld = world1;
    worldAnimator.simulationStepNanos = 100_000L;
    worldAnimator.currentWorldTime = 0L;
    worldAnimator.firstRunTime = 0L;
    worldAnimator.lastTimeRendered = 0L;
    Reflector.setField(worldAnimator, "initialWorld", world);
    worldAnimator.lastSimTimeRendered = 0L;
    Reflector.setField(my1, "this$0", worldAnimator);
    Reflector.setField(my1, "val$canvas", canvas);
    PowerMockito.whenNew("net.barbux.creatures2d.WorldAnimator$1").withParameterTypes(WorldAnimator.class, Canvas.class).withArguments(or(isA(WorldAnimator.class), isNull(WorldAnimator.class)), or(isA(Canvas.class), isNull(Canvas.class))).thenReturn(my1);
    final WorldAnimator actual = new WorldAnimator(world, canvas);
    Assert.assertEquals(1.0, actual.speed, 0.0);
    Assert.assertNotNull(actual.animatedWorld);
    final ArrayList<Creature> arrayList2 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList2, actual.animatedWorld.creatures);
    Assert.assertEquals(0L, actual.animatedWorld.lastUpdateNanos);
    Assert.assertNull(actual.animatedWorld.physicsSupplier);
    Assert.assertEquals(100_000L, actual.simulationStepNanos);
    Assert.assertNotNull(actual.initialWorld);
    final ArrayList<Creature> arrayList3 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList3, actual.initialWorld.creatures);
    Assert.assertEquals(2L, actual.initialWorld.lastUpdateNanos);
    Assert.assertNull(actual.initialWorld.physicsSupplier);
  }

  @PrepareForTest(fullyQualifiedNames = {"net.barbux.creatures2d.WorldAnimator$1"}, value = WorldAnimator.class)
  @Test
  public void constructorInputNotNullNullOutputVoid() throws Exception, InvocationTargetException {
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList arrayList = new ArrayList();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    final Supplier supplier = (Supplier) Reflector.getInstance("java.util.function.Supplier");
    Reflector.setField(world, "physicsSupplier", supplier);
    final Canvas canvas = null;
    final AnimationTimer my1 = (AnimationTimer) PowerMockito.mock(Class.forName("net.barbux.creatures2d.WorldAnimator$1"));
    final WorldAnimator worldAnimator = (WorldAnimator) Reflector.getInstance("net.barbux.creatures2d.WorldAnimator");
    worldAnimator.speed = 1.0;
    worldAnimator.animationTimer = null;
    final World world1 = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList arrayList1 = new ArrayList();
    Reflector.setField(world1, "creatures", arrayList1);
    world1.lastUpdateNanos = 0L;
    Reflector.setField(world1, "physicsSupplier", supplier);
    worldAnimator.animatedWorld = world1;
    worldAnimator.simulationStepNanos = 100_000L;
    worldAnimator.currentWorldTime = 0L;
    worldAnimator.firstRunTime = 0L;
    worldAnimator.lastTimeRendered = 0L;
    Reflector.setField(worldAnimator, "initialWorld", world);
    worldAnimator.lastSimTimeRendered = 0L;
    Reflector.setField(my1, "this$0", worldAnimator);
    Reflector.setField(my1, "val$canvas", null);
    PowerMockito.whenNew("net.barbux.creatures2d.WorldAnimator$1").withParameterTypes(WorldAnimator.class, Canvas.class).withArguments(or(isA(WorldAnimator.class), isNull(WorldAnimator.class)), or(isA(Canvas.class), isNull(Canvas.class))).thenReturn(my1);
    final WorldAnimator actual = new WorldAnimator(world, canvas);
    Assert.assertEquals(1.0, actual.speed, 0.0);
    Assert.assertNotNull(actual.animatedWorld);
    final ArrayList<Creature> arrayList2 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList2, actual.animatedWorld.creatures);
    Assert.assertEquals(0L, actual.animatedWorld.lastUpdateNanos);
    Assert.assertNotNull(actual.animatedWorld.physicsSupplier);
    Assert.assertEquals(100_000L, actual.simulationStepNanos);
    Assert.assertNotNull(actual.initialWorld);
    final ArrayList<Creature> arrayList3 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList3, actual.initialWorld.creatures);
    Assert.assertEquals(2L, actual.initialWorld.lastUpdateNanos);
    Assert.assertEquals(actual.animatedWorld.physicsSupplier, actual.initialWorld.physicsSupplier);
  }

  @PrepareForTest(AnimationTimer.class)
  @Test
  public void startOutputVoid() throws Exception, InvocationTargetException {
    final WorldAnimator worldAnimator = (WorldAnimator) Reflector.getInstance("net.barbux.creatures2d.WorldAnimator");
    worldAnimator.speed = 2.0;
    final AnimationTimer animationTimer = PowerMockito.mock(AnimationTimer.class);
    worldAnimator.animationTimer = animationTimer;
    worldAnimator.animatedWorld = null;
    worldAnimator.simulationStepNanos = 2L;
    worldAnimator.currentWorldTime = 2L;
    worldAnimator.firstRunTime = 4L;
    worldAnimator.lastTimeRendered = 2L;
    Reflector.setField(worldAnimator, "initialWorld", null);
    worldAnimator.lastSimTimeRendered = 4L;
    worldAnimator.start();

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest(AnimationTimer.class)
  @Test
  public void stopOutputVoid() throws Exception, InvocationTargetException {
    final WorldAnimator worldAnimator = (WorldAnimator) Reflector.getInstance("net.barbux.creatures2d.WorldAnimator");
    worldAnimator.speed = 2.0;
    final AnimationTimer animationTimer = PowerMockito.mock(AnimationTimer.class);
    worldAnimator.animationTimer = animationTimer;
    worldAnimator.animatedWorld = null;
    worldAnimator.simulationStepNanos = 2L;
    worldAnimator.currentWorldTime = 2L;
    worldAnimator.firstRunTime = 4L;
    worldAnimator.lastTimeRendered = 2L;
    Reflector.setField(worldAnimator, "initialWorld", null);
    worldAnimator.lastSimTimeRendered = 4L;
    worldAnimator.stop();

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void resetOutputVoid() throws InvocationTargetException {
    final WorldAnimator worldAnimator = (WorldAnimator) Reflector.getInstance("net.barbux.creatures2d.WorldAnimator");
    worldAnimator.speed = 2.0;
    final AnimationTimer animationTimer = (AnimationTimer) Reflector.getInstance("javafx.animation.AnimationTimer");
    worldAnimator.animationTimer = animationTimer;
    worldAnimator.animatedWorld = null;
    worldAnimator.simulationStepNanos = 2L;
    worldAnimator.currentWorldTime = 2L;
    worldAnimator.firstRunTime = 2L;
    worldAnimator.lastTimeRendered = 2L;
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    Reflector.setField(worldAnimator, "initialWorld", world);
    worldAnimator.lastSimTimeRendered = 16L;
    worldAnimator.reset();
    Assert.assertEquals(1.0, worldAnimator.speed, 0.0);
    Assert.assertNotNull(worldAnimator.animatedWorld);
    final ArrayList<Creature> arrayList1 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList1, worldAnimator.animatedWorld.creatures);
    Assert.assertEquals(0L, worldAnimator.animatedWorld.lastUpdateNanos);
    Assert.assertNull(worldAnimator.animatedWorld.physicsSupplier);
    Assert.assertEquals(0L, worldAnimator.currentWorldTime);
    Assert.assertEquals(0L, worldAnimator.firstRunTime);
    Assert.assertEquals(0L, worldAnimator.lastTimeRendered);
    Assert.assertEquals(0L, worldAnimator.lastSimTimeRendered);
  }

  @Test
  public void resetOutputVoid1() throws InvocationTargetException {
    final WorldAnimator worldAnimator = (WorldAnimator) Reflector.getInstance("net.barbux.creatures2d.WorldAnimator");
    worldAnimator.speed = 2.0;
    final AnimationTimer animationTimer = (AnimationTimer) Reflector.getInstance("javafx.animation.AnimationTimer");
    worldAnimator.animationTimer = animationTimer;
    worldAnimator.animatedWorld = null;
    worldAnimator.simulationStepNanos = 2L;
    worldAnimator.currentWorldTime = 2L;
    worldAnimator.firstRunTime = 2L;
    worldAnimator.lastTimeRendered = 2L;
    final World world = (World) Reflector.getInstance("net.barbux.creatures2d.World");
    final ArrayList<Creature> arrayList = new ArrayList<Creature>();
    Reflector.setField(world, "creatures", arrayList);
    world.lastUpdateNanos = 2L;
    Reflector.setField(world, "physicsSupplier", null);
    Reflector.setField(worldAnimator, "initialWorld", world);
    worldAnimator.lastSimTimeRendered = 4L;
    worldAnimator.reset();
    Assert.assertEquals(1.0, worldAnimator.speed, 0.0);
    Assert.assertNotNull(worldAnimator.animatedWorld);
    final ArrayList<Creature> arrayList1 = new ArrayList<Creature>();
    Assert.assertEquals(arrayList1, worldAnimator.animatedWorld.creatures);
    Assert.assertEquals(0L, worldAnimator.animatedWorld.lastUpdateNanos);
    Assert.assertNull(worldAnimator.animatedWorld.physicsSupplier);
    Assert.assertEquals(0L, worldAnimator.currentWorldTime);
    Assert.assertEquals(0L, worldAnimator.firstRunTime);
    Assert.assertEquals(0L, worldAnimator.lastTimeRendered);
    Assert.assertEquals(0L, worldAnimator.lastSimTimeRendered);
  }
}
