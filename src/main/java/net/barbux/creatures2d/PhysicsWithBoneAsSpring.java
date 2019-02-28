package net.barbux.creatures2d;

public class PhysicsWithBoneAsSpring implements Physics {

    double currentTimeSeconds;

    void keepInBox(Creature.Node node) {
        // bounce
        if (node.p.x < node.size / 2) {
            node.v.x = -node.v.x * 0.85;
        }
        if (node.p.y < node.size / 2) {
            node.v.y = -node.v.y * 0.85;
        }

        node.p.x = Math.max(node.size / 2, node.p.x);
        node.p.y = Math.max(node.size / 2, node.p.y);
    }

    public void applyForce(Creature.Connection connection, double time, double seconds, double power) {
        double currentDistance =  Math.sqrt(Geometry.distance2(connection.node1.p, connection.node2.p));
        double previousExpectedLength = connection.expectedLength;
        connection.setExpectedLength(time);
        double diff = connection.expectedLength - currentDistance;
        if (diff < -EPSILON || diff > EPSILON) {
            double force = diff * 1000000d / currentDistance;
            double speed = (connection.expectedLength - previousExpectedLength) / seconds;
            if (speed < 0) {
                speed = -speed;
            }
            // force * speed must be <= power
            if (force > 0 && force * speed > power) {
                force = power / speed;
            } else if (force < 0 && -force * speed > power) {
                force = -power / speed;
            }

            Geometry.Vector diffVector = Geometry.diffVector(connection.node1.p, connection.node2.p);
            connection.node1.f.plusEqual(diffVector, force);
            connection.node2.f.minusEqual(diffVector, force);
        }
    }

    @Override
    public void update(long nanos, Creature creature) {
        double seconds = nanos / 1_000_000_000d;
        currentTimeSeconds += seconds;
        double initialEnergy = 0d;
        for (Creature.Node node : creature.allNodes) {
            Geometry.Vector f = node.f;
            f.zero();
            initialEnergy += node.savedEnergy;


            // gravity
            f.y += -g * node.weight;

            // air resistance
            f.plusEqual(node.v, -airLamda);
        }

        for (Creature.Bone bone : creature.allBones) {
            applyForce(bone, currentTimeSeconds, seconds, 0.0);
        }

        for (Creature.Muscle muscle : creature.allMuscles) {
            //initialEnergy += muscle.power * seconds;
            applyForce(muscle, currentTimeSeconds, seconds, muscle.power);
        }

        for (Creature.Node node : creature.allNodes) {
            Geometry.Vector f = node.f;
            // F = ma  => a = F / m
            Geometry.Vector a = new Geometry.Vector(f.x / node.weight, f.y / node.weight);

            node.v.plusEqual(a, seconds);
        }

        for (Creature.Node node : creature.allNodes) {
            // friction (non linear force)
            Geometry.Vector f = node.f;
            if (node.p.y <= EPSILON + node.size / 2) {
                final double fx;
                if (node.v.x < 0) {
                    fx = 5 * node.weight;
                } else if (node.v.x > 0) {
                    fx = -5 * node.weight;
                } else {
                    fx = 0.0;
                }
                double ax = fx / node.weight;
                double newvx = node.v.x + ax * seconds;
                if ((newvx > 0 && node.v.x < 0) || (newvx < 0 && node.v.x > 0)) {
                    node.v.x = 0;
                } else {
                    node.v.x = newvx;
                }
            }

            node.v.clipEpsilon();

        }

        double finalEnergy = 0.0;
        for (Creature.Node node : creature.allNodes) {
            finalEnergy += node.getEnergy();
        }

        if (finalEnergy > initialEnergy) {
            double ratio = initialEnergy / finalEnergy;

            for (Creature.Node node : creature.allNodes) {
                node.scaleEnergy(ratio);
            }
        }

        for (Creature.Node node : creature.allNodes) {
            node.savedEnergy = node.getEnergy();
            node.p.plusEqual(node.v, seconds);
            keepInBox(node);
        }
    }
}
