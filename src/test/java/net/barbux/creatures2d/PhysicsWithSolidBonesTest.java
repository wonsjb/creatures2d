package net.barbux.creatures2d;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isA;
import static org.mockito.Matchers.isNull;

import com.diffblue.deeptestutils.Reflector;
import net.barbux.creatures2d.Creature.Bone;
import net.barbux.creatures2d.Creature.Muscle;
import net.barbux.creatures2d.Creature.Node;
import net.barbux.creatures2d.Creature.NodeList;
import net.barbux.creatures2d.Creature;
import net.barbux.creatures2d.PhysicsWithSolidBones.Solid;
import net.barbux.creatures2d.PhysicsWithSolidBones.SolidCandidate;
import net.barbux.creatures2d.PhysicsWithSolidBones;
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
import java.util.BitSet;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Predicate;

@RunWith(PowerMockRunner.class)
public class PhysicsWithSolidBonesTest {

  @Test
  public void constructorOutputNotNull() {
    final PhysicsWithSolidBones actual = new PhysicsWithSolidBones();
    Assert.assertNotNull(actual);
  }

  @PrepareForTest({ArrayList.class, PhysicsWithSolidBones.class})
  @Test
  public void initializeInputNotNullOutputVoid() throws Exception, InvocationTargetException {
    final PhysicsWithSolidBones physicsWithSolidBones = new PhysicsWithSolidBones();
    final ArrayList<PhysicsWithSolidBones.Solid> arrayList = new ArrayList<PhysicsWithSolidBones.Solid>();
    Reflector.setField(physicsWithSolidBones, "solids", arrayList);
    final Creature creature = new Creature();
    final ArrayList<Creature.Bone> arrayList1 = new ArrayList<Creature.Bone>();
    Reflector.setField(creature, "allBones", arrayList1);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", creature);
    final ArrayList arrayList2 = new ArrayList();
    Reflector.setField(nodeList, "internal", arrayList2);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 0.0;
    final ArrayList<Creature.Muscle> arrayList3 = new ArrayList<Creature.Muscle>();
    Reflector.setField(creature, "allMuscles", arrayList3);
    creature.creatureId = 0;
    final Random random = PowerMockito.mock(Random.class);
    PowerMockito.whenNew(Random.class).withParameterTypes(long.class).withArguments(anyLong()).thenReturn(random);
    final ArrayList arrayList4 = PowerMockito.mock(ArrayList.class);
    final Iterator itr1 = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr1, "this$0", arrayList4);
    final Iterator itr = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr, "this$0", arrayList4);
    PowerMockito.when(arrayList4.iterator()).thenReturn(itr).thenReturn(itr1);
    PowerMockito.when(arrayList4.removeIf(or(isA(Predicate.class), isNull(Predicate.class)))).thenReturn(false);
    Reflector.setField(arrayList4, "modCount", 0);
    PowerMockito.whenNew(ArrayList.class).withNoArguments().thenReturn(arrayList4);
    physicsWithSolidBones.initialize(creature);

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest({ArrayList.class, PhysicsWithSolidBones.class})
  @Test
  public void initializeInputNotNullOutputVoid1() throws Exception, InvocationTargetException {
    final PhysicsWithSolidBones physicsWithSolidBones = new PhysicsWithSolidBones();
    final ArrayList<PhysicsWithSolidBones.Solid> arrayList = new ArrayList<PhysicsWithSolidBones.Solid>();
    Reflector.setField(physicsWithSolidBones, "solids", arrayList);
    final Creature creature = new Creature();
    final ArrayList<Creature.Bone> arrayList1 = new ArrayList<Creature.Bone>();
    Reflector.setField(creature, "allBones", arrayList1);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", creature);
    final ArrayList arrayList2 = new ArrayList();
    Reflector.setField(nodeList, "internal", arrayList2);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 0.0;
    final ArrayList<Creature.Muscle> arrayList3 = new ArrayList<Creature.Muscle>();
    Reflector.setField(creature, "allMuscles", arrayList3);
    creature.creatureId = 0;
    final Random random = PowerMockito.mock(Random.class);
    PowerMockito.whenNew(Random.class).withParameterTypes(long.class).withArguments(anyLong()).thenReturn(random);
    final ArrayList arrayList4 = PowerMockito.mock(ArrayList.class);
    final Iterator itr1 = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr1, "this$0", arrayList4);
    Reflector.setField(itr1, "expectedModCount", 0);
    Reflector.setField(itr1, "lastRet", -1);
    Reflector.setField(itr1, "cursor", 0);
    final Iterator itr = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr, "this$0", arrayList4);
    Reflector.setField(itr, "expectedModCount", 0);
    Reflector.setField(itr, "lastRet", -1);
    Reflector.setField(itr, "cursor", 0);
    PowerMockito.when(arrayList4.iterator()).thenReturn(itr).thenReturn(itr1);
    PowerMockito.when(arrayList4.removeIf(or(isA(Predicate.class), isNull(Predicate.class)))).thenReturn(false);
    final Object[] objectArray = { null, null, null, null, null, null, null, null, null, null };
    Reflector.setField(arrayList4, "elementData", objectArray);
    Reflector.setField(arrayList4, "size", 0);
    Reflector.setField(arrayList4, "modCount", 0);
    PowerMockito.whenNew(ArrayList.class).withNoArguments().thenReturn(arrayList4);
    physicsWithSolidBones.initialize(creature);

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest({ArrayList.class, PhysicsWithSolidBones.class})
  @Test
  public void updateInputPositiveOutputVoid() throws Exception, InvocationTargetException {
    final PhysicsWithSolidBones physicsWithSolidBones = new PhysicsWithSolidBones();
    final ArrayList<PhysicsWithSolidBones.Solid> arrayList = new ArrayList<PhysicsWithSolidBones.Solid>();
    Reflector.setField(physicsWithSolidBones, "solids", arrayList);
    final long nanos = 2L;
    final Random random = PowerMockito.mock(Random.class);
    PowerMockito.whenNew(Random.class).withParameterTypes(long.class).withArguments(anyLong()).thenReturn(random);
    final ArrayList arrayList1 = PowerMockito.mock(ArrayList.class);
    final Iterator itr1 = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr1, "this$0", arrayList1);
    Reflector.setField(itr1, "expectedModCount", 0);
    Reflector.setField(itr1, "lastRet", -1);
    Reflector.setField(itr1, "cursor", 0);
    final Iterator itr = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr, "this$0", arrayList1);
    Reflector.setField(itr, "expectedModCount", 0);
    Reflector.setField(itr, "lastRet", -1);
    Reflector.setField(itr, "cursor", 0);
    PowerMockito.when(arrayList1.iterator()).thenReturn(itr).thenReturn(itr1);
    PowerMockito.when(arrayList1.removeIf(or(isA(Predicate.class), isNull(Predicate.class)))).thenReturn(false);
    final Object[] objectArray = { null, null, null, null, null, null, null, null, null, null };
    Reflector.setField(arrayList1, "elementData", objectArray);
    Reflector.setField(arrayList1, "size", 0);
    Reflector.setField(arrayList1, "modCount", 0);
    PowerMockito.whenNew(ArrayList.class).withNoArguments().thenReturn(arrayList1);
    physicsWithSolidBones.update(nanos);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void updateInputPositiveOutputVoid1() {
    final PhysicsWithSolidBones physicsWithSolidBones = new PhysicsWithSolidBones();
    physicsWithSolidBones.update(2L);

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest({ArrayList.class, PhysicsWithSolidBones.class})
  @Test
  public void updateInputPositiveOutputVoid2() throws Exception, InvocationTargetException {
    final PhysicsWithSolidBones physicsWithSolidBones = new PhysicsWithSolidBones();
    final ArrayList<PhysicsWithSolidBones.Solid> arrayList = new ArrayList<PhysicsWithSolidBones.Solid>();
    Reflector.setField(physicsWithSolidBones, "solids", arrayList);
    final long nanos = 2L;
    final Random random = PowerMockito.mock(Random.class);
    PowerMockito.whenNew(Random.class).withParameterTypes(long.class).withArguments(anyLong()).thenReturn(random);
    final ArrayList arrayList1 = PowerMockito.mock(ArrayList.class);
    final Iterator itr1 = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr1, "this$0", arrayList1);
    final Iterator itr = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr, "this$0", arrayList1);
    PowerMockito.when(arrayList1.iterator()).thenReturn(itr).thenReturn(itr1);
    PowerMockito.when(arrayList1.removeIf(or(isA(Predicate.class), isNull(Predicate.class)))).thenReturn(false);
    Reflector.setField(arrayList1, "modCount", 0);
    PowerMockito.whenNew(ArrayList.class).withNoArguments().thenReturn(arrayList1);
    physicsWithSolidBones.update(nanos);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void mergeInputNotNullOutputVoid() {
    final PhysicsWithSolidBones.SolidCandidate physicsWithSolidBonesSolidCandidate = new PhysicsWithSolidBones.SolidCandidate();
    final PhysicsWithSolidBones.SolidCandidate other = new PhysicsWithSolidBones.SolidCandidate();
    physicsWithSolidBonesSolidCandidate.merge(other);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void isSameSolidInputNotNull1OutputFalse() throws InvocationTargetException {
    final PhysicsWithSolidBones.SolidCandidate physicsWithSolidBonesSolidCandidate = new PhysicsWithSolidBones.SolidCandidate();
    physicsWithSolidBonesSolidCandidate.solid = null;
    final BitSet bitSet = new BitSet();
    bitSet.set(2);
    physicsWithSolidBonesSolidCandidate.nodes = bitSet;
    final PhysicsWithSolidBones.SolidCandidate other = new PhysicsWithSolidBones.SolidCandidate();
    final PhysicsWithSolidBones.Solid solid = (PhysicsWithSolidBones.Solid) Reflector.getInstance("net.barbux.creatures2d.PhysicsWithSolidBones$Solid");
    Reflector.setField(solid, "color", null);
    Reflector.setField(solid, "nodes", null);
    Reflector.setField(solid, "bones", null);
    other.solid = solid;
    final BitSet bitSet1 = new BitSet();
    bitSet1.set(2);
    other.nodes = bitSet1;
    final ArrayList<Creature.Bone> allBones = new ArrayList<Creature.Bone>();
    final Creature.Bone bone = (Creature.Bone) Reflector.getInstance("net.barbux.creatures2d.Creature$Bone");
    bone.color = null;
    Reflector.setField(bone, "length", 2.0);
    bone.expectedLength = 2.0;
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 3;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 2.0);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    Reflector.setField(bone, "node1", node);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node1, "originalPos", null);
    node1.nodeId = 2;
    node1.color = null;
    Reflector.setField(node1, "v", null);
    Reflector.setField(node1, "weight", 2.0);
    Reflector.setField(node1, "size", 2.0);
    Reflector.setField(node1, "p", null);
    Reflector.setField(bone, "node2", node1);
    allBones.add(bone);
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.isSameSolid(other, allBones));
  }

  @Test
  public void isSameSolidInputNotNull1OutputFalse1() throws InvocationTargetException {
    final PhysicsWithSolidBones.SolidCandidate physicsWithSolidBonesSolidCandidate = new PhysicsWithSolidBones.SolidCandidate();
    physicsWithSolidBonesSolidCandidate.solid = null;
    final BitSet bitSet = new BitSet();
    bitSet.set(3);
    physicsWithSolidBonesSolidCandidate.nodes = bitSet;
    final PhysicsWithSolidBones.SolidCandidate other = new PhysicsWithSolidBones.SolidCandidate();
    final PhysicsWithSolidBones.Solid solid = (PhysicsWithSolidBones.Solid) Reflector.getInstance("net.barbux.creatures2d.PhysicsWithSolidBones$Solid");
    Reflector.setField(solid, "color", null);
    Reflector.setField(solid, "nodes", null);
    Reflector.setField(solid, "bones", null);
    other.solid = solid;
    final BitSet bitSet1 = new BitSet();
    bitSet1.set(3);
    other.nodes = bitSet1;
    final ArrayList<Creature.Bone> allBones = new ArrayList<Creature.Bone>();
    final Creature.Bone bone = (Creature.Bone) Reflector.getInstance("net.barbux.creatures2d.Creature$Bone");
    bone.color = null;
    Reflector.setField(bone, "length", 2.0);
    bone.expectedLength = 2.0;
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 3;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 2.0);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    Reflector.setField(bone, "node1", node);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node1, "originalPos", null);
    node1.nodeId = 3;
    node1.color = null;
    Reflector.setField(node1, "v", null);
    Reflector.setField(node1, "weight", 2.0);
    Reflector.setField(node1, "size", 2.0);
    Reflector.setField(node1, "p", null);
    Reflector.setField(bone, "node2", node1);
    allBones.add(bone);
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.isSameSolid(other, allBones));
  }

  @Test
  public void isSameSolidInputNotNull1OutputFalse2() throws InvocationTargetException {
    final PhysicsWithSolidBones.SolidCandidate physicsWithSolidBonesSolidCandidate = new PhysicsWithSolidBones.SolidCandidate();
    physicsWithSolidBonesSolidCandidate.solid = null;
    final BitSet bitSet = new BitSet();
    bitSet.set(65);
    physicsWithSolidBonesSolidCandidate.nodes = bitSet;
    final PhysicsWithSolidBones.SolidCandidate other = new PhysicsWithSolidBones.SolidCandidate();
    final PhysicsWithSolidBones.Solid solid = (PhysicsWithSolidBones.Solid) Reflector.getInstance("net.barbux.creatures2d.PhysicsWithSolidBones$Solid");
    Reflector.setField(solid, "color", null);
    Reflector.setField(solid, "nodes", null);
    Reflector.setField(solid, "bones", null);
    other.solid = solid;
    final BitSet bitSet1 = new BitSet();
    bitSet1.set(65);
    other.nodes = bitSet1;
    final ArrayList<Creature.Bone> allBones = new ArrayList<Creature.Bone>();
    final Creature.Bone bone = (Creature.Bone) Reflector.getInstance("net.barbux.creatures2d.Creature$Bone");
    bone.color = null;
    Reflector.setField(bone, "length", 2.0);
    bone.expectedLength = 2.0;
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 193;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 2.0);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    Reflector.setField(bone, "node1", node);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node1, "originalPos", null);
    node1.nodeId = 69;
    node1.color = null;
    Reflector.setField(node1, "v", null);
    Reflector.setField(node1, "weight", 2.0);
    Reflector.setField(node1, "size", 2.0);
    Reflector.setField(node1, "p", null);
    Reflector.setField(bone, "node2", node1);
    allBones.add(bone);
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.isSameSolid(other, allBones));
  }

  @PrepareForTest({ArrayList.class, PhysicsWithSolidBones.class})
  @Test
  public void initializeInputNotNullOutputVoid2() throws Exception, InvocationTargetException {
    final PhysicsWithSolidBones physicsWithSolidBones = new PhysicsWithSolidBones();
    Reflector.setField(physicsWithSolidBones, "solids", null);
    final Creature creature = new Creature();
    final ArrayList<Creature.Bone> arrayList = new ArrayList<Creature.Bone>();
    Reflector.setField(creature, "allBones", arrayList);
    Reflector.setField(creature, "allNodes", null);
    creature.physics = null;
    creature.maxReached = 2.0;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    final Random random = PowerMockito.mock(Random.class);
    PowerMockito.whenNew(Random.class).withParameterTypes(long.class).withArguments(anyLong()).thenReturn(random);
    final ArrayList arrayList1 = PowerMockito.mock(ArrayList.class);
    final Iterator itr1 = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr1, "this$0", arrayList1);
    final Iterator itr = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr, "this$0", arrayList1);
    PowerMockito.when(arrayList1.iterator()).thenReturn(itr).thenReturn(itr1);
    PowerMockito.when(arrayList1.removeIf(or(isA(Predicate.class), isNull(Predicate.class)))).thenReturn(false);
    Reflector.setField(arrayList1, "modCount", 0);
    PowerMockito.whenNew(ArrayList.class).withNoArguments().thenReturn(arrayList1);
    physicsWithSolidBones.initialize(creature);

    // The method returns void, testing that no exception is thrown
  }

  @PrepareForTest({ArrayList.class, PhysicsWithSolidBones.class})
  @Test
  public void initializeInputNotNullOutputVoid3() throws Exception, InvocationTargetException {
    final PhysicsWithSolidBones physicsWithSolidBones = new PhysicsWithSolidBones();
    Reflector.setField(physicsWithSolidBones, "solids", null);
    final Creature creature = new Creature();
    final ArrayList<Creature.Bone> arrayList = new ArrayList<Creature.Bone>();
    Reflector.setField(creature, "allBones", arrayList);
    Reflector.setField(creature, "allNodes", null);
    final PhysicsWithSolidBones physicsWithSolidBones1 = new PhysicsWithSolidBones();
    Reflector.setField(physicsWithSolidBones1, "solids", null);
    creature.physics = physicsWithSolidBones1;
    creature.maxReached = 2.0;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    final Random random = PowerMockito.mock(Random.class);
    PowerMockito.whenNew(Random.class).withParameterTypes(long.class).withArguments(anyLong()).thenReturn(random);
    final ArrayList arrayList1 = PowerMockito.mock(ArrayList.class);
    final Iterator itr1 = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr1, "this$0", arrayList1);
    final Iterator itr = (Iterator) Reflector.getInstance("java.util.ArrayList$Itr");
    Reflector.setField(itr, "this$0", arrayList1);
    PowerMockito.when(arrayList1.iterator()).thenReturn(itr).thenReturn(itr1);
    PowerMockito.when(arrayList1.removeIf(or(isA(Predicate.class), isNull(Predicate.class)))).thenReturn(false);
    Reflector.setField(arrayList1, "modCount", 0);
    PowerMockito.whenNew(ArrayList.class).withNoArguments().thenReturn(arrayList1);
    physicsWithSolidBones.initialize(creature);

    // The method returns void, testing that no exception is thrown
  }

}
