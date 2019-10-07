package net.barbux.creatures2d;

import net.barbux.creatures2d.ScrewTheory.Point3D;
import net.barbux.creatures2d.ScrewTheory.Screw;
import net.barbux.creatures2d.ScrewTheory.Vector3D;
import org.junit.Assert;
import org.junit.Test;

public class ScrewTheoryTest {
  @Test
  public void productInputNotNullNotNullOutputNotNull() {
    final Point3D screwTheory$Point3D = new Point3D(0.0, 0.0, 0.0);
    final Point3D screwTheory$Point3D1 = new Point3D(0.0, 0.0, 0.0);
    final Vector3D v1 = new Vector3D(screwTheory$Point3D, screwTheory$Point3D1);
    final Point3D screwTheory$Point3D2 = new Point3D(0.0, 0.0, 0.0);
    final Point3D screwTheory$Point3D3 = new Point3D(0.0, 0.0, 0.0);
    final Vector3D v2 = new Vector3D(screwTheory$Point3D2, screwTheory$Point3D3);
    final Vector3D actual = ScrewTheory.product(v1, v2);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0.0, actual.z, 0.0);
    Assert.assertEquals(0.0, actual.x, 0.0);
    Assert.assertEquals(0.0, actual.y, 0.0);
  }

  @Test
  public void constructorInputZeroZeroZeroOutputNotNull2() {
    final Point3D actual = new Point3D(0.0, 0.0, 0.0);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0.0, actual.z, 0.0);
    Assert.assertEquals(0.0, actual.x, 0.0);
    Assert.assertEquals(0.0, actual.y, 0.0);
  }

  @Test
  public void constructorOutputNotNull2() {
    final Screw actual = new Screw();
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.M);
    Assert.assertNull(actual.point);
    Assert.assertNull(actual.R);
  }

  @Test
  public void constructorInputNotNullNotNullOutputNotNull() {
    final Point3D A = new Point3D(0.0, 0.0, 0.0);
    final Point3D B = new Point3D(0.0, 0.0, 0.0);
    final Vector3D actual = new Vector3D(A, B);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0.0, actual.z, 0.0);
    Assert.assertEquals(0.0, actual.x, 0.0);
    Assert.assertEquals(0.0, actual.y, 0.0);
  }

  @Test
  public void constructorInputZeroZeroZeroOutputNotNull() {
    final Vector3D actual = new Vector3D(0.0, 0.0, 0.0);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0.0, actual.z, 0.0);
    Assert.assertEquals(0.0, actual.x, 0.0);
    Assert.assertEquals(0.0, actual.y, 0.0);
  }
}