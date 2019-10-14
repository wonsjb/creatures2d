package net.barbux.creatures2d;

import net.barbux.creatures2d.Creature.Bone;
import net.barbux.creatures2d.Creature.Node;
import net.barbux.creatures2d.PhysicsWithSolidBones.Solid;
import net.barbux.creatures2d.PhysicsWithSolidBones.SolidCandidate;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.BitSet;

public class PhysicsWithSolidBonesTest {

  @Test
  public void constructorOutputNotNull() {
    final SolidCandidate actual = new SolidCandidate();
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.solid);
    final BitSet bitSet = new BitSet();
    Assert.assertEquals(bitSet, actual.nodes);
  }

  @Test
  public void hasNodeInputNotNullOutputFalse() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    final Node node = new Node(2.0, 2.0);
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.hasNode(node));
  }

  @Test
  public void isEmptyOutputTrue() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    Assert.assertTrue(physicsWithSolidBonesSolidCandidate.isEmpty());
  }

  @Test
  public void isSameSolidInputNotNullNotNullOutputFalse() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    final SolidCandidate other = new SolidCandidate();
    final ArrayList<Bone> allBones = new ArrayList<Bone>();
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.isSameSolid(other, allBones));
  }

  @Test
  public void isSameSolidInputNotNull0OutputFalse() {
    final SolidCandidate physicsWithSolidBonesSolidCandidate = new SolidCandidate();
    physicsWithSolidBonesSolidCandidate.solid = null;
    final BitSet bitSet = new BitSet();
    bitSet.set(2);
    physicsWithSolidBonesSolidCandidate.nodes = bitSet;
    final SolidCandidate other = new SolidCandidate();
    other.solid = null;
    final BitSet bitSet1 = new BitSet();
    bitSet1.set(2);
    other.nodes = bitSet1;
    final ArrayList<Bone> allBones = new ArrayList<Bone>();
    Assert.assertFalse(physicsWithSolidBonesSolidCandidate.isSameSolid(other, allBones));
  }

  @Test
  public void constructorInputNullOutputNotNull() {
    final Solid actual = new Solid(null);
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.color);
    final ArrayList<Node> arrayList = new ArrayList<Node>();
    Assert.assertEquals(arrayList, actual.nodes);
    final ArrayList<Bone> arrayList1 = new ArrayList<Bone>();
    Assert.assertEquals(arrayList1, actual.bones);
  }
}
