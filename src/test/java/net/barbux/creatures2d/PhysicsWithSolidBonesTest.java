package net.barbux.creatures2d;

import java.util.ArrayList;
import java.util.BitSet;
import net.barbux.creatures2d.Creature.Bone;
import net.barbux.creatures2d.Creature.Node;
import net.barbux.creatures2d.PhysicsWithSolidBones.SolidCandidate;
import org.junit.Assert;
import org.junit.Test;

public class PhysicsWithSolidBonesTest {
  @Test
  public void hasNodeInputNotNullOutputFalse2() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    final SolidCandidate physicsWithSolidBones$SolidCandidate = new SolidCandidate();
    physicsWithSolidBonesSolidCandidate.merge(physicsWithSolidBones$SolidCandidate);
    final Node node = new Node(0.0, 0.0);
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.hasNode(node));
  }

  @Test
  public void hasNodeInputNotNullOutputFalse() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    final Node node = new Node(0.0, 0.0);
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.hasNode(node));
  }

  @Test
  public void isSameSolidInputNotNullNotNullOutputFalse() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    final SolidCandidate other = new SolidCandidate();
    final ArrayList<Bone> allBones = new ArrayList<Bone>();
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.isSameSolid(other, allBones));
  }

  @Test
  public void constructorOutputNotNull2() {
    final SolidCandidate actual = new SolidCandidate();
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.solid);
    final BitSet bitSet = new BitSet();
    Assert.assertEquals(bitSet, actual.nodes);
  }

  @Test
  public void isEmptyOutputTrue() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    Assert.assertTrue(physicsWithSolidBonesSolidCandidate.isEmpty());
  }
}