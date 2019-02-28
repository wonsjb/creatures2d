package net.barbux.creatures2d;

public class Geometry {

    final static double EPSILON = 1e-6;

    static final class Vector {
        double x;
        double y;

        Vector(double x, double y) {
            this.x = x;
            this.y = y;
        }

        void plusEqual(Vector other, double scalar) {
            x += other.x * scalar;
            y += other.y * scalar;
        }

        void minusEqual(Vector other, double scalar) {
            x -= other.x * scalar;
            y -= other.y * scalar;
        }

        void scale(double scalar) {
            x *= scalar;
            y *= scalar;
        }

        void clipEpsilon() {
            if (y > -EPSILON && y < EPSILON) {
                y = 0;
            }
            if (x > -EPSILON && x < EPSILON) {
                x = 0;
            }
        }

        void zero() {
            x = 0;
            y = 0;
        }

        public Vector clone() {
            return new Vector(x, y);
        }
    }

    static double distance2(Vector p1, Vector p2) {
        double d1 = p1.x - p2.x;
        double d2 = p1.y - p2.y;
        return d1 * d1 + d2 * d2;
    }

    static Vector diffVector(Vector p1, Vector p2) {
        return new Vector(p1.x - p2.x, p1.y - p2.y);
    }
}
