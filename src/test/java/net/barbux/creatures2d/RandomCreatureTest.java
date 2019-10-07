package net.barbux.creatures2d;

import net.barbux.creatures2d.RandomCreature.PairOfNode;
import org.junit.Assert;
import org.junit.Test;

public class RandomCreatureTest {
  @Test
  public void hashCodeOutputZero2() {
    final PairOfNode randomCreaturePairOfNode = new PairOfNode(-31, 0);
    Assert.assertEquals(0, randomCreaturePairOfNode.hashCode());
  }

  @Test
  public void constructorInputZeroZeroOutputNotNull() {
    final PairOfNode actual = new PairOfNode(0, 0);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0, actual.node2);
    Assert.assertEquals(0, actual.node1);
  }
}