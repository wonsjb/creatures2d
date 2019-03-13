package net.barbux.creatures2d;

/**
 * Set of classes / functions that implement the Screw Theory
 */
public class ScrewTheory {
    public static class Vector3D {
        double x;
        double y;
        double z;

        Vector3D(double x, double y , double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        Vector3D(Point3D A, Point3D B) {
            x = B.x - A.x;
            y = B.y - A.y;
            z = B.z - A.z;
        }

        void plusEqual(Vector3D other) {
            x += other.x;
            y += other.y;
            z += other.z;
        }
    }

    static Vector3D product(Vector3D v1, Vector3D v2) {
        double x = v1.y * v2.z - v1.z * v2.y;
        double y = v1.z * v2.x - v1.x * v2.z;
        double z = v1.x * v2.y - v1.y * v2.x;
        return new Vector3D(x, y, z);
    }

    public static class Point3D {
        double x;
        double y;
        double z;

        Point3D(double x, double y , double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Screw {
        Point3D point;
        Vector3D R;
        Vector3D M;

        void moveTo(Point3D otherPoint) {
            M.plusEqual(product(new Vector3D(otherPoint, point), R));
            point = otherPoint;
        }
    }


}
