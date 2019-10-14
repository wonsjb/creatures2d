package net.barbux.creatures2d;

import net.barbux.creatures2d.ScrewTheory.Vector3D;
import org.junit.Assert;
import org.junit.Test;

public class ScrewTheoryTest {

  @Test
  public void constructorInputPositivePositivePositiveOutputNotNull() {
    final Vector3D actual = new Vector3D(2.0, 2.0, 2.0);
    Assert.assertNotNull(actual);
    Assert.assertEquals(2.0, actual.z, 0.0);
    Assert.assertEquals(2.0, actual.x, 0.0);
    Assert.assertEquals(2.0, actual.y, 0.0);
  }

  @Test
  public void productInputNotNullNotNullOutputNotNull() {
    final ScrewTheory.Point3D screwTheory$Point3D = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final ScrewTheory.Point3D screwTheory$Point3D1 = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final Vector3D v1 = new Vector3D(screwTheory$Point3D, screwTheory$Point3D1);
    final ScrewTheory.Point3D screwTheory$Point3D2 = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final ScrewTheory.Point3D screwTheory$Point3D3 = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final Vector3D v2 = new Vector3D(screwTheory$Point3D2, screwTheory$Point3D3);
    final Vector3D actual = ScrewTheory.product(v1, v2);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0.0, actual.z, 0.0);
    Assert.assertEquals(0.0, actual.x, 0.0);
    Assert.assertEquals(0.0, actual.y, 0.0);
  }

  @Test
  public void constructorInputNotNullNotNullOutputNotNull() {
    final ScrewTheory.Point3D A = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final ScrewTheory.Point3D B = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final Vector3D actual = new Vector3D(A, B);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0.0, actual.z, 0.0);
    Assert.assertEquals(0.0, actual.x, 0.0);
    Assert.assertEquals(0.0, actual.y, 0.0);
  }

  @Test
  public void constructorInputPositivePositivePositiveOutputNotNull1() {
    final ScrewTheory.Point3D actual = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    Assert.assertNotNull(actual);
    Assert.assertEquals(2.0, actual.z, 0.0);
    Assert.assertEquals(2.0, actual.x, 0.0);
    Assert.assertEquals(2.0, actual.y, 0.0);
  }

  @Test
  public void constructorOutputNotNull() {
    final ScrewTheory.Screw actual = new ScrewTheory.Screw();
    Assert.assertNotNull(actual);
    Assert.assertNull(actual.M);
    Assert.assertNull(actual.point);
    Assert.assertNull(actual.R);
  }
}
