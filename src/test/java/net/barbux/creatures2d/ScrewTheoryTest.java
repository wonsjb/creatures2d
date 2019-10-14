package net.barbux.creatures2d;

import com.diffblue.deeptestutils.Reflector;
import net.barbux.creatures2d.ScrewTheory.Point3D;
import net.barbux.creatures2d.ScrewTheory.Screw;
import net.barbux.creatures2d.ScrewTheory.Vector3D;
import net.barbux.creatures2d.ScrewTheory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

public class ScrewTheoryTest {

  @Test
  public void constructorOutputNotNull() {
    final ScrewTheory actual = new ScrewTheory();
    Assert.assertNotNull(actual);
  }

  @Test
  public void moveToInputNotNullOutputVoid() throws InvocationTargetException {
    final ScrewTheory.Screw screwTheoryScrew = new ScrewTheory.Screw();
    final ScrewTheory.Vector3D vector3D = (ScrewTheory.Vector3D) Reflector.getInstance("net.barbux.creatures2d.ScrewTheory$Vector3D");
    vector3D.z = 8.0;
    vector3D.x = 224.0;
    vector3D.y = 1024.0;
    screwTheoryScrew.M = vector3D;
    final ScrewTheory.Point3D point3D = (ScrewTheory.Point3D) Reflector.getInstance("net.barbux.creatures2d.ScrewTheory$Point3D");
    point3D.z = 1024.0;
    point3D.x = 112.0;
    point3D.y = 63.0;
    screwTheoryScrew.point = point3D;
    final ScrewTheory.Vector3D vector3D1 = (ScrewTheory.Vector3D) Reflector.getInstance("net.barbux.creatures2d.ScrewTheory$Vector3D");
    vector3D1.z = 7.0;
    vector3D1.x = 2.0;
    vector3D1.y = 1024.0;
    screwTheoryScrew.R = vector3D1;
    final ScrewTheory.Point3D otherPoint = (ScrewTheory.Point3D) Reflector.getInstance("net.barbux.creatures2d.ScrewTheory$Point3D");
    otherPoint.z = 959.0;
    otherPoint.x = 112.0;
    otherPoint.y = 63.0;
    screwTheoryScrew.moveTo(otherPoint);
    Assert.assertNotNull(screwTheoryScrew.M);
    Assert.assertEquals(8.0, screwTheoryScrew.M.z, 0.0);
    Assert.assertEquals(-66336.0, screwTheoryScrew.M.x, 0.0);
    Assert.assertEquals(1154.0, screwTheoryScrew.M.y, 0.0);
    Assert.assertNotNull(screwTheoryScrew.point);
    Assert.assertEquals(959.0, screwTheoryScrew.point.z, 0.0);
    Assert.assertEquals(112.0, screwTheoryScrew.point.x, 0.0);
    Assert.assertEquals(63.0, screwTheoryScrew.point.y, 0.0);
  }

  @Test
  public void plusEqualInputNotNullOutputVoid() {
    final ScrewTheory.Point3D screwTheory$Point3D = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final ScrewTheory.Point3D screwTheory$Point3D1 = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final ScrewTheory.Vector3D screwTheoryVector3D = new ScrewTheory.Vector3D(screwTheory$Point3D, screwTheory$Point3D1);
    final ScrewTheory.Point3D screwTheory$Point3D2 = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final ScrewTheory.Point3D screwTheory$Point3D3 = new ScrewTheory.Point3D(2.0, 2.0, 2.0);
    final ScrewTheory.Vector3D other = new ScrewTheory.Vector3D(screwTheory$Point3D2, screwTheory$Point3D3);
    screwTheoryVector3D.plusEqual(other);

    // The method returns void, testing that no exception is thrown
  }
}
