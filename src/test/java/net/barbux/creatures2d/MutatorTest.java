package net.barbux.creatures2d;

import net.barbux.creatures2d.Mutator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MutatorTest {

  @Test
  public void constructorOutputNotNull() {
    final Mutator actual = new Mutator();
    Assert.assertNotNull(actual);
  }
}
