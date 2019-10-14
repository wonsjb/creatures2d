package net.barbux.creatures2d;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class PhysicsWithBoneAsSpringTest {

  @Test
  public void constructorOutputNotNull() {
    final PhysicsWithBoneAsSpring actual = new PhysicsWithBoneAsSpring();
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.creature);
  }

  @Test
  public void initializeInputNotNullOutputVoid() {
    final PhysicsWithBoneAsSpring physicsWithBoneAsSpring = new PhysicsWithBoneAsSpring();
    final Creature creature = new Creature();
    physicsWithBoneAsSpring.initialize(creature);
    Assert.assertNotNull(physicsWithBoneAsSpring.creature);
    final ArrayList<Creature.Bone> arrayList = new ArrayList<Creature.Bone>();
    Assert.assertEquals(arrayList, physicsWithBoneAsSpring.creature.allBones);
    Assert.assertNotNull(physicsWithBoneAsSpring.creature.allNodes);
    Assert.assertNull(physicsWithBoneAsSpring.creature.physics);
    Assert.assertEquals(0.0, physicsWithBoneAsSpring.creature.maxReached, 0.0);
    final ArrayList<Creature.Muscle> arrayList1 = new ArrayList<Creature.Muscle>();
    Assert.assertEquals(arrayList1, physicsWithBoneAsSpring.creature.allMuscles);
    Assert.assertEquals(0, physicsWithBoneAsSpring.creature.creatureId);
  }

  @Test
  public void getEnergyOutputPositive() {
    final Creature.Node creature$Node = new Creature.Node(0x1.00000000001p+1 /* 2.0 */, 0x1.8d9ffede021cdp+7 /* 198.812 */);
    final PhysicsWithBoneAsSpring.NodeData physicsWithBoneAsSpringNodeData = new PhysicsWithBoneAsSpring.NodeData(creature$Node);
    Assert.assertEquals(0x1.e7966f403cd4ap+10 /* 1950.35 */, physicsWithBoneAsSpringNodeData.getEnergy(), 0.0);
  }
}
