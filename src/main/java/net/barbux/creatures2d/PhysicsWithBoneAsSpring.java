package net.barbux.creatures2d;

import java.util.ArrayList;
import java.util.List;

public class PhysicsWithBoneAsSpring implements Physics {

    static class NodeData {
        final Geometry.Vector f = new Geometry.Vector(0, 0);
        double savedEnergy;
        final Creature.Node node;

        NodeData(Creature.Node node) {
            this.node = node;
            savedEnergy = getEnergy();
        }

        double getEnergy() {
            return 0.5 * node.weight * (node.v.x * node.v.x + node.v.y * node.v.y) + 9.81 * node.weight * node.p.y;
        }

        public void scaleEnergy(double ratio) {
            // 0.5Vnew2 + p shoudl equal ratio * (0.5 Vold2 + p)
            // 0.5 Vnew2 = ratio(0.5 Vold2 + p ) - p
            double p = 9.81 * node.p.y;
            double Vold2 =  (node.v.x * node.v.x + node.v.y * node.v.y);
            double Vnew2 = 2 * (ratio * (0.5 * Vold2 + p) - p);

            // x2 + y2 = Vold2
            // t2x2 + t2y2 = Vnew2
            // t2(x2 + y2) = Vnew2
            // t2(Vold2) = Vnew2
            // t2 = Vnew2 / Vold2
            double t2 = Vnew2 / Vold2;
            if (t2 > 0) {
                node.v.scale(Math.sqrt(t2));
            }
        }
    }

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
            double force = diff * 100_000d / currentDistance;
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
            allNodeData.get(connection.node1.nodeId).f.plusEqual(diffVector, force);
            allNodeData.get(connection.node2.nodeId).f.minusEqual(diffVector, force);
        }
    }

    @Override
    public void update(long nanos) {
        double seconds = nanos / 1_000_000_000d;
        currentTimeSeconds += seconds;
        double initialEnergy = 0d;
        for (NodeData nodeData : allNodeData) {
            Geometry.Vector f = nodeData.f;
            f.zero();
            initialEnergy += nodeData.savedEnergy;


            // gravity
            f.y += -g * nodeData.node.weight;

            // air resistance
            f.plusEqual(nodeData.node.v, -airLamda);
        }

        for (Creature.Bone bone : creature.allBones) {
            applyForce(bone, currentTimeSeconds, seconds, 0.0);
        }

        for (Creature.Muscle muscle : creature.allMuscles) {
            //initialEnergy += muscle.power * seconds;
            applyForce(muscle, currentTimeSeconds, seconds, muscle.power);
        }

        for (NodeData nodeData : allNodeData) {
            Geometry.Vector f = nodeData.f;
            // F = ma  => a = F / m
            Geometry.Vector a = new Geometry.Vector(f.x / nodeData.node.weight, f.y / nodeData.node.weight);

            nodeData.node.v.plusEqual(a, seconds);
        }

        for (Creature.Node node : creature.allNodes) {
            // friction (non linear force)
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
        for (NodeData nodeData : allNodeData) {
            finalEnergy += nodeData.getEnergy();
        }

        if (finalEnergy > initialEnergy) {
            double ratio = initialEnergy / finalEnergy;

            for (NodeData nodeData : allNodeData) {
                nodeData.scaleEnergy(ratio);
            }
        }

        for (NodeData nodeData : allNodeData) {
            nodeData.savedEnergy = nodeData.getEnergy();
            nodeData.node.p.plusEqual(nodeData.node.v, seconds);
            keepInBox(nodeData.node);
        }
    }

    List<NodeData> allNodeData = new ArrayList<>();
    Creature creature;

    @Override
    public void initialize(Creature creature) {
        this.creature = creature;
        for (Creature.Node node : creature.allNodes) {
            if (node.nodeId != allNodeData.size()) {
                throw new RuntimeException("Nodes are not in order :(");
            }
            allNodeData.add(new NodeData(node));
        }
    }
}
