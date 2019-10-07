package net.barbux.creatures2d;

import java.util.ArrayList;
import net.barbux.creatures2d.Creature.Bone;
import net.barbux.creatures2d.Creature.Muscle;
import net.barbux.creatures2d.Creature.Node;
import net.barbux.creatures2d.PhysicsWithBoneAsSpring.NodeData;
import org.junit.Assert;
import org.junit.Test;

public class PhysicsWithBoneAsSpringTest {
  @Test
  public void scaleEnergyInputNegativeOutputVoid() {
    final Node creature$Node = new Node(0.0, -71746.0);
    final NodeData physicsWithBoneAsSpringNodeData = new NodeData(creature$Node);
    physicsWithBoneAsSpringNodeData.scaleEnergy(-0x1.d7d801ecp+30);
    Assert.assertNotNull(physicsWithBoneAsSpringNodeData.node);
    Assert.assertNotNull(physicsWithBoneAsSpringNodeData.node.originalPos);
    Assert.assertEquals(-71746.0, physicsWithBoneAsSpringNodeData.node.originalPos.y, 0.0);
    Assert.assertEquals(0.0, physicsWithBoneAsSpringNodeData.node.originalPos.x, 0.0);
    Assert.assertEquals(0, physicsWithBoneAsSpringNodeData.node.nodeId);
    Assert.assertNotNull(physicsWithBoneAsSpringNodeData.node.color);
    Assert.assertNotNull(physicsWithBoneAsSpringNodeData.node.v);
    Assert.assertEquals(Double.NaN, physicsWithBoneAsSpringNodeData.node.v.y, 0.0);
    Assert.assertEquals(Double.NaN, physicsWithBoneAsSpringNodeData.node.v.x, 0.0);
    Assert.assertEquals(1.0, physicsWithBoneAsSpringNodeData.node.weight, 0.0);
    Assert.assertEquals(0.05, physicsWithBoneAsSpringNodeData.node.size, 0.0);
    Assert.assertNotNull(physicsWithBoneAsSpringNodeData.node.p);
    Assert.assertEquals(-71746.0, physicsWithBoneAsSpringNodeData.node.p.y, 0.0);
    Assert.assertEquals(0.0, physicsWithBoneAsSpringNodeData.node.p.x, 0.0);
  }

  @Test
  public void initializeInputNotNullOutputVoid() {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature = new Creature();
    physicsWithBoneAsSpring.initialize(creature);
    Assert.assertNotNull(physicsWithBoneAsSpring.creature);
    final ArrayList<Bone> arrayList = new ArrayList<Bone>();
    Assert.assertEquals(arrayList, physicsWithBoneAsSpring.creature.allBones);
    Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
    Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
    Assert.assertEquals(0.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
    final ArrayList<Muscle> arrayList1 = new ArrayList<Muscle>();
    Assert.assertEquals(arrayList1, physicsWithBoneAsSpring.creature.allMuscles);
    Assert.assertEquals(0, physicsWithBoneAsSpring.creature.creatureId);
  }

  @Test
  public void constructorOutputNotNull() {
    final PhysicsWithBoneAsSpring actual = new PhysicsWithBoneAsSpring();
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.creature);
  }
}