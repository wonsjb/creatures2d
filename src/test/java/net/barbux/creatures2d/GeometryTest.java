package net.barbux.creatures2d;

import net.barbux.creatures2d.Geometry.Vector;
import net.barbux.creatures2d.Geometry;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.geometry.Point2D;

public class GeometryTest {

  @Test
  public void constructorOutputNotNull() {
    final Geometry actual = new Geometry();
    Assert.assertNotNull(actual);
  }
}
