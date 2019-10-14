package net.barbux.creatures2d;

import net.barbux.creatures2d.Geometry.Vector;
import org.junit.Assert;
import org.junit.Test;

import javafx.geometry.Point2D;

public class GeometryTest {

  @Test
  public void clipEpsilonOutputVoid() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Geometry.Vector geometryVector = new Geometry.Vector(point2D);
    geometryVector.clipEpsilon();
  }

  @Test
  public void cloneOutputNotNull() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Vector geometryVector = new Vector(point2D);
    final Vector actual = geometryVector.clone();
    Assert.assertNotNull(actual);
    Assert.assertEquals(2.0, actual.y, 0.0);
    Assert.assertEquals(2.0, actual.x, 0.0);
  }

  @Test
  public void constructorInputPositivePositiveOutputNotNull() {
    final Vector actual = new Vector(2.0, 2.0);
    Assert.assertNotNull(actual);
    Assert.assertEquals(2.0, actual.y, 0.0);
    Assert.assertEquals(2.0, actual.x, 0.0);
  }

  @Test
  public void diffVectorInputNotNullNotNullOutputNotNull() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Vector p1 = new Vector(point2D);
    final Point2D point2D1 = new Point2D(2.0, 2.0);
    final Vector p2 = new Vector(point2D1);
    final Vector actual = Geometry.diffVector(p1, p2);
    Assert.assertNotNull(actual);
    Assert.assertEquals(0.0, actual.y, 0.0);
    Assert.assertEquals(0.0, actual.x, 0.0);
  }

  @Test
  public void constructorInputNotNullOutputNotNull() {
    final Point2D point = new Point2D(2.0, 2.0);
    final Vector actual = new Vector(point);
    Assert.assertNotNull(actual);
    Assert.assertEquals(2.0, actual.y, 0.0);
    Assert.assertEquals(2.0, actual.x, 0.0);
  }

  @Test
  public void distance2InputNotNullNotNullOutputZero() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Vector p1 = new Vector(point2D);
    final Point2D point2D1 = new Point2D(2.0, 2.0);
    final Vector p2 = new Vector(point2D1);
    Assert.assertEquals(0.0, Geometry.distance2(p1, p2), 0.0);
  }

  @Test
  public void minusEqualInputNotNullPositiveOutputVoid() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Vector geometryVector = new Vector(point2D);
    final Point2D point2D1 = new Point2D(2.0, 2.0);
    final Vector other = new Vector(point2D1);
    geometryVector.minusEqual(other, 2.0);
    Assert.assertEquals(-2.0, geometryVector.y, 0.0);
    Assert.assertEquals(-2.0, geometryVector.x, 0.0);
  }

  @Test
  public void plusEqualInputNotNullPositiveOutputVoid() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Vector geometryVector = new Vector(point2D);
    final Point2D point2D1 = new Point2D(2.0, 2.0);
    final Vector other = new Vector(point2D1);
    geometryVector.plusEqual(other, 2.0);
    Assert.assertEquals(6.0, geometryVector.y, 0.0);
    Assert.assertEquals(6.0, geometryVector.x, 0.0);
  }

  @Test
  public void scaleInputPositiveOutputVoid() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Vector geometryVector = new Vector(point2D);
    geometryVector.scale(2.0);
    Assert.assertEquals(4.0, geometryVector.y, 0.0);
    Assert.assertEquals(4.0, geometryVector.x, 0.0);
  }

  @Test
  public void zeroOutputVoid() {
    final Point2D point2D = new Point2D(2.0, 2.0);
    final Vector geometryVector = new Vector(point2D);
    geometryVector.zero();
    Assert.assertEquals(0.0, geometryVector.y, 0.0);
    Assert.assertEquals(0.0, geometryVector.x, 0.0);
  }

  @Test
  public void constructorInputNegativeNegativeOutputNotNull() {
    final double arg0 = -1e-06;
    final double arg1 = -1e-06;
    final Vector actual = new Vector(arg0, arg1);
    Assert.assertNotNull(actual);
    Assert.assertEquals(-1e-06, actual.x, 0.0);
    Assert.assertEquals(-1e-06, actual.y, 0.0);
  }

  @Test
  public void minusEqualInputNotNullNegativeOutputVoid() {
    final Vector thisObj = new Vector(-1e-06, -1e-06);
    final Vector arg0 = new Vector(-1e-06, -1e-06);
    final double arg1 = -1e-06;
    thisObj.minusEqual(arg0, arg1);
    Assert.assertEquals(-0x1.0c6f8ba2f85ap-20 /* -1e-06 */, thisObj.x, 0.0);
    Assert.assertEquals(-0x1.0c6f8ba2f85ap-20 /* -1e-06 */, thisObj.y, 0.0);
  }

  @Test
  public void plusEqualInputNotNullNegativeOutputVoid() {
    final Vector thisObj = new Vector(-1e-06, -1e-06);
    final Vector arg0 = new Vector(-1e-06, -1e-06);
    final double arg1 = -1e-06;
    thisObj.plusEqual(arg0, arg1);
    Assert.assertEquals(-9.99999e-07, thisObj.x, 0.0);
    Assert.assertEquals(-9.99999e-07, thisObj.y, 0.0);
  }

  @Test
  public void scaleInputNegativeOutputVoid() {
    final Vector thisObj = new Vector(-1e-06, -1e-06);
    final double arg0 = -1e-06;
    thisObj.scale(arg0);
    Assert.assertEquals(1e-12, thisObj.x, 0.0);
    Assert.assertEquals(1e-12, thisObj.y, 0.0);
  }
}
