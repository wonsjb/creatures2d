package net.barbux.creatures2d;

import com.diffblue.deeptestutils.Reflector;
import net.barbux.creatures2d.Creature.Bone;
import net.barbux.creatures2d.Creature.Muscle;
import net.barbux.creatures2d.Creature.Node;
import net.barbux.creatures2d.Creature.NodeList;
import net.barbux.creatures2d.Creature;
import net.barbux.creatures2d.Geometry.Vector;
import net.barbux.creatures2d.PhysicsWithBoneAsSpring.NodeData;
import net.barbux.creatures2d.PhysicsWithBoneAsSpring;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class PhysicsWithBoneAsSpringTest {

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Test
  public void initializeInputNotNullOutputVoid() {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature1 = new Creature();
    physicsWithBoneAsSpring.initialize(creature1);
    physicsWithBoneAsSpring.update(1024L);
    final Creature creature = new Creature();
    physicsWithBoneAsSpring.initialize(creature);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void updateInputPositiveOutputVoid() {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature = new Creature();
    physicsWithBoneAsSpring.initialize(creature);
    physicsWithBoneAsSpring.update(269L);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void updateInputPositiveOutputVoid1() {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature = new Creature();
    physicsWithBoneAsSpring.initialize(creature);
    physicsWithBoneAsSpring.update(1024L);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void updateInputPositiveOutputVoid2() {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature = new Creature();
    physicsWithBoneAsSpring.initialize(creature);
    physicsWithBoneAsSpring.update(113L);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    physicsWithBoneAsSpring.creature = null;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 2.0);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    arrayList.add(null);
    arrayList.add(null);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", null);
    final ArrayList<Creature.Node> arrayList1 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 2;
    node.color = null;
    final Vector vector = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector.y = 0x1.5a02f4105dee7p+1 /* 2.70322 */;
    vector.x = 2.0;
    Reflector.setField(node, "v", vector);
    Reflector.setField(node, "weight", 0x1.79399bd8e157fp+8 /* 377.225 */);
    Reflector.setField(node, "size", 2.0);
    final Vector vector1 = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector1.y = 0x1.41c9cfb728a0ep+3 /* 10.0559 */;
    vector1.x = 2.0;
    Reflector.setField(node, "p", vector1);
    arrayList1.add(node);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node1, "originalPos", null);
    node1.nodeId = 130;
    node1.color = null;
    Reflector.setField(node1, "v", null);
    Reflector.setField(node1, "weight", 0x1.f8c14aff01542p+4 /* 31.5472 */);
    Reflector.setField(node1, "size", 2.0);
    Reflector.setField(node1, "p", null);
    arrayList1.add(node1);
    final Creature.Node node2 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node2, "originalPos", null);
    node2.nodeId = 130;
    node2.color = null;
    Reflector.setField(node2, "v", null);
    Reflector.setField(node2, "weight", 0x1.1eff554193fb7p+8 /* 286.997 */);
    Reflector.setField(node2, "size", 2.0);
    Reflector.setField(node2, "p", null);
    arrayList1.add(node2);
    Reflector.setField(nodeList, "internal", arrayList1);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 2.0;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(2.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException1() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature1 = new Creature();
    Reflector.setField(creature1, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    final Creature creature2 = new Creature();
    Reflector.setField(creature2, "allBones", null);
    Reflector.setField(creature2, "allNodes", null);
    creature2.physics = null;
    creature2.maxReached = 0x1.000000000008p+1 /* 2.0 */;
    Reflector.setField(creature2, "allMuscles", null);
    creature2.creatureId = 2;
    Reflector.setField(nodeList, "this$0", creature2);
    Reflector.setField(nodeList, "internal", null);
    Reflector.setField(creature1, "allNodes", nodeList);
    creature1.physics = null;
    creature1.maxReached = 512.0;
    Reflector.setField(creature1, "allMuscles", null);
    creature1.creatureId = 2;
    physicsWithBoneAsSpring.creature = creature1;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 2.0);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    arrayList.add(null);
    arrayList.add(null);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    final Creature.NodeList nodeList1 = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList1, "this$0", null);
    final ArrayList<Creature.Node> arrayList1 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 2;
    node.color = null;
    final Vector vector = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector.y = 0x1.bb270065dc391p+4 /* 27.697 */;
    vector.x = 0x1.57d5a468230c4p+9 /* 687.669 */;
    Reflector.setField(node, "v", vector);
    Reflector.setField(node, "weight", 0x1.0800000006196p+8 /* 264.0 */);
    Reflector.setField(node, "size", 2.0);
    final Vector vector1 = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector1.y = 768.0;
    vector1.x = 8.0;
    Reflector.setField(node, "p", vector1);
    arrayList1.add(node);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node1, "originalPos", null);
    node1.nodeId = 252;
    node1.color = null;
    Reflector.setField(node1, "v", null);
    Reflector.setField(node1, "weight", 0x1.6a56460b9a41ap+4 /* 22.6461 */);
    Reflector.setField(node1, "size", 2.125);
    Reflector.setField(node1, "p", null);
    arrayList1.add(node1);
    Reflector.setField(nodeList1, "internal", arrayList1);
    Reflector.setField(creature, "allNodes", nodeList1);
    creature.physics = null;
    creature.maxReached = 0x1.0000002p+1 /* 2.0 */;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(0x1.0000002p+1 /* 2.0 */, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException2() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    physicsWithBoneAsSpring.creature = null;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 0x1.0000000000001p+1 /* 2.0 */);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", null);
    final ArrayList<Creature.Node> arrayList1 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 5;
    node.color = null;
    final Vector vector = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector.y = 0x1.4b99fd6db808ap+2 /* 5.18127 */;
    vector.x = 0x1.2cc662584d1cbp+1 /* 2.3498 */;
    Reflector.setField(node, "v", vector);
    Reflector.setField(node, "weight", 0x1.81c4p+7 /* 192.883 */);
    Reflector.setField(node, "size", 2.0);
    final Vector vector1 = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector1.y = 0x1.9901a7c9028d8p+8 /* 409.006 */;
    vector1.x = 2.0;
    Reflector.setField(node, "p", vector1);
    arrayList1.add(node);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    final Vector vector2 = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector2.y = 2.0;
    vector2.x = 2.0;
    Reflector.setField(node1, "originalPos", vector2);
    node1.nodeId = 38;
    node1.color = null;
    final Vector vector3 = (Vector) Reflector.getInstance("net.barbux.creatures2d.Geometry$Vector");
    vector3.y = 0x1.4b99fd6db808ap+2 /* 5.18127 */;
    vector3.x = 0x1.2cc662584d1cbp+1 /* 2.3498 */;
    Reflector.setField(node1, "v", vector3);
    Reflector.setField(node1, "weight", 0x1.81c4p+7 /* 192.883 */);
    Reflector.setField(node1, "size", 2.0);
    Reflector.setField(node1, "p", null);
    arrayList1.add(node1);
    arrayList1.add(null);
    arrayList1.add(null);
    arrayList1.add(null);
    arrayList1.add(null);
    Reflector.setField(nodeList, "internal", arrayList1);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 2.0;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(2.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }

  @Test
  public void scaleEnergyInputPositiveOutputVoid() {
    final Creature.Node creature$Node = new Creature.Node(1024.0, 25.0);
    final PhysicsWithBoneAsSpring.NodeData physicsWithBoneAsSpringNodeData = new PhysicsWithBoneAsSpring.NodeData(creature$Node);
    physicsWithBoneAsSpringNodeData.scaleEnergy(2.0);
    physicsWithBoneAsSpringNodeData.scaleEnergy(192.0);

    // The method returns void, testing that no exception is thrown
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException3() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    physicsWithBoneAsSpring.creature = null;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 2.0);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    final PhysicsWithBoneAsSpring.NodeData nodeData = (PhysicsWithBoneAsSpring.NodeData) Reflector.getInstance("net.barbux.creatures2d.PhysicsWithBoneAsSpring$NodeData");
    Reflector.setField(nodeData, "node", null);
    Reflector.setField(nodeData, "f", null);
    nodeData.savedEnergy = 2.0;
    arrayList.add(nodeData);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", null);
    final ArrayList<Creature.Node> arrayList1 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 2;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 151.0);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    arrayList1.add(node);
    Reflector.setField(nodeList, "internal", arrayList1);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 2.0;
    final ArrayList<Creature.Muscle> arrayList2 = new ArrayList<Creature.Muscle>();
    arrayList2.add(null);
    Reflector.setField(creature, "allMuscles", arrayList2);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(2.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      final ArrayList<Creature.Muscle> arrayList3 = new ArrayList<Creature.Muscle>();
      arrayList3.add(null);
      Assert.assertEquals(arrayList3, physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException4() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    physicsWithBoneAsSpring.creature = null;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 2.0);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", null);
    final ArrayList<Creature.Node> arrayList1 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 128;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 0x1.f92e5bff39055p+9 /* 1010.36 */);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    arrayList1.add(node);
    arrayList1.add(null);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node1, "originalPos", null);
    node1.nodeId = 130;
    node1.color = null;
    Reflector.setField(node1, "v", null);
    Reflector.setField(node1, "weight", 0x1.804p+3 /* 12.0078 */);
    Reflector.setField(node1, "size", 2.0);
    Reflector.setField(node1, "p", null);
    arrayList1.add(node1);
    Reflector.setField(nodeList, "internal", arrayList1);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 2.0;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(2.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException5() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature1 = new Creature();
    Reflector.setField(creature1, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    final Creature creature2 = new Creature();
    Reflector.setField(creature2, "allBones", null);
    Reflector.setField(creature2, "allNodes", null);
    creature2.physics = null;
    creature2.maxReached = 0x1.000000000008p+1 /* 2.0 */;
    Reflector.setField(creature2, "allMuscles", null);
    creature2.creatureId = 2;
    Reflector.setField(nodeList, "this$0", creature2);
    Reflector.setField(nodeList, "internal", null);
    Reflector.setField(creature1, "allNodes", nodeList);
    creature1.physics = null;
    creature1.maxReached = 512.0;
    Reflector.setField(creature1, "allMuscles", null);
    creature1.creatureId = 2;
    physicsWithBoneAsSpring.creature = creature1;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 2.0);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    arrayList.add(null);
    arrayList.add(null);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    final Creature.NodeList nodeList1 = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList1, "this$0", null);
    final ArrayList<Creature.Node> arrayList1 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 250;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 0x1.0800000006196p+8 /* 264.0 */);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    arrayList1.add(node);
    final Creature.Node node1 = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node1, "originalPos", null);
    node1.nodeId = 252;
    node1.color = null;
    Reflector.setField(node1, "v", null);
    Reflector.setField(node1, "weight", 0x1.6a56460b9a41ap+4 /* 22.6461 */);
    Reflector.setField(node1, "size", 2.125);
    Reflector.setField(node1, "p", null);
    arrayList1.add(node1);
    Reflector.setField(nodeList1, "internal", arrayList1);
    Reflector.setField(creature, "allNodes", nodeList1);
    creature.physics = null;
    creature.maxReached = 0x1.0000002p+1 /* 2.0 */;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(0x1.0000002p+1 /* 2.0 */, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException6() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    physicsWithBoneAsSpring.creature = null;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 0x1.0000000000001p+1 /* 2.0 */);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    arrayList.add(null);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    Reflector.setField(creature, "allBones", null);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", null);
    final ArrayList<Creature.Node> arrayList1 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 6;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 0x1.81c4p+7 /* 192.883 */);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    arrayList1.add(node);
    arrayList1.add(null);
    arrayList1.add(null);
    arrayList1.add(null);
    arrayList1.add(null);
    arrayList1.add(null);
    Reflector.setField(nodeList, "internal", arrayList1);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 2.0;
    Reflector.setField(creature, "allMuscles", null);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(2.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      Assert.assertNull(physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }

  @Test
  public void initializeInputNotNullOutputRuntimeException7() throws InvocationTargetException {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    physicsWithBoneAsSpring.creature = null;
    Reflector.setField(physicsWithBoneAsSpring, "currentTimeSeconds", 2.0);
    final ArrayList<PhysicsWithBoneAsSpring.NodeData> arrayList = new ArrayList<PhysicsWithBoneAsSpring.NodeData>();
    arrayList.add(null);
    Reflector.setField(physicsWithBoneAsSpring, "allNodeData", arrayList);
    final Creature creature = new Creature();
    final ArrayList<Creature.Bone> arrayList1 = new ArrayList<Creature.Bone>();
    arrayList1.add(null);
    Reflector.setField(creature, "allBones", arrayList1);
    final Creature.NodeList nodeList = (Creature.NodeList) Reflector.getInstance("net.barbux.creatures2d.Creature$NodeList");
    Reflector.setField(nodeList, "this$0", null);
    final ArrayList<Creature.Node> arrayList2 = new ArrayList<Creature.Node>();
    final Creature.Node node = (Creature.Node) Reflector.getInstance("net.barbux.creatures2d.Creature$Node");
    Reflector.setField(node, "originalPos", null);
    node.nodeId = 2;
    node.color = null;
    Reflector.setField(node, "v", null);
    Reflector.setField(node, "weight", 5.0);
    Reflector.setField(node, "size", 2.0);
    Reflector.setField(node, "p", null);
    arrayList2.add(node);
    Reflector.setField(nodeList, "internal", arrayList2);
    Reflector.setField(creature, "allNodes", nodeList);
    creature.physics = null;
    creature.maxReached = 2.0;
    final ArrayList<Creature.Muscle> arrayList3 = new ArrayList<Creature.Muscle>();
    arrayList3.add(null);
    Reflector.setField(creature, "allMuscles", arrayList3);
    creature.creatureId = 2;
    try {

      // Act
      thrown.expect(RuntimeException.class);
      physicsWithBoneAsSpring.initialize(creature);
    } catch (RuntimeException ex) {

      // Assert side effects
      Assert.assertNotNull(physicsWithBoneAsSpring.creature);
      final ArrayList<Creature.Bone> arrayList4 = new ArrayList<Creature.Bone>();
      arrayList4.add(null);
      Assert.assertEquals(arrayList4, physicsWithBoneAsSpring.creature.allBones);
      Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
      Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
      Assert.assertEquals(2.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
      final ArrayList<Creature.Muscle> arrayList5 = new ArrayList<Creature.Muscle>();
      arrayList5.add(null);
      Assert.assertEquals(arrayList5, physicsWithBoneAsSpring.creature.allMuscles);
      Assert.assertEquals(2, physicsWithBoneAsSpring.creature.creatureId);
      throw ex;
    }
  }
}
