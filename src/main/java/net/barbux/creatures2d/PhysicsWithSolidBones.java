package net.barbux.creatures2d;


import javafx.scene.paint.Color;

import java.util.*;

/**
 * Problem with handling bones as strong springs is that the force is so big, that double precision error introduce a
 * lot of errors in the simulation. This is an attempt as using solid physics instead. (as I remember it from
 * university)
 *
 * https://en.wikipedia.org/wiki/Screw_theory
 */
public class PhysicsWithSolidBones implements Physics{



    @Override
    public void update(long nanos) {

    }

    static class SolidCandidate
    {
        BitSet nodes = new BitSet();
        Solid solid;

        boolean isEmpty() {
            return nodes.isEmpty();
        }

        boolean isSameSolid(SolidCandidate other, List<Creature.Bone> allBones) {
            // 2 solids are the same if:
            // 1) they have 2 points in common
            // 2) they have 1 point in common + a connection between them

            BitSet intersection = (BitSet)nodes.clone();
            intersection.and(other.nodes);

            int cardinality = intersection.cardinality();
            if (cardinality >= 2) {
                return true;
            }
            if (cardinality == 1) {
                int sameNode = intersection.nextSetBit(0);
                for (Creature.Bone bone : allBones) {
                    if (bone.node1.nodeId != sameNode && bone.node2.nodeId != sameNode) {
                        if ((nodes.get(bone.node1.nodeId) && other.nodes.get(bone.node2.nodeId)) ||
                                (nodes.get(bone.node2.nodeId) && other.nodes.get(bone.node1.nodeId))) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        void merge(SolidCandidate other) {
            nodes.or(other.nodes);
            other.nodes.clear();
        }

        boolean hasNode(Creature.Node node) {
            return nodes.get(node.nodeId);
        }
    }

    static class Solid
    {
        final List<Creature.Node> nodes = new ArrayList<>();
        final List<Creature.Bone> bones = new ArrayList<>();
        final Color color;

        Solid(Color color) {
            this.color = color;
        }
    }

    private List<Solid> solids = new ArrayList<>();

    @Override
    public void initialize(Creature creature) {

        List<SolidCandidate> candidates = new ArrayList<>();
        for (Creature.Bone bone : creature.allBones) {
            SolidCandidate candidate = new SolidCandidate();
            candidate.nodes.set(bone.node1.nodeId);
            candidate.nodes.set(bone.node2.nodeId);
            candidates.add(candidate);
        }

        boolean done;
        do {
            done = true;
            for (SolidCandidate s1 : candidates) {
                if (s1.isEmpty()) {
                    continue;
                }
                for (SolidCandidate s2 : candidates) {
                    if (s1 == s2 || s2.isEmpty()) {
                        continue;
                    }
                    if (s1.isSameSolid(s2, creature.allBones)) {
                        done = false;
                        s1.merge(s2);
                    }
                }
            }
        } while (!done);

        candidates.removeIf(SolidCandidate::isEmpty);

        Random random = new Random(0);

        for (SolidCandidate candidate : candidates) {
            Solid solid = new Solid(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
            candidate.solid = solid;
            for (int i = candidate.nodes.nextSetBit(0); i != -1; i = candidate.nodes.nextSetBit(i + 1)) {
                solid.nodes.add(creature.allNodes.get(i));
            }
            solids.add(solid);
        }

        for (Creature.Bone bone : creature.allBones) {
            for (SolidCandidate candidate : candidates) {
                if (candidate.hasNode(bone.node1) && candidate.hasNode(bone.node2)) {
                    candidate.solid.bones.add(bone);
                    bone.color = candidate.solid.color;
                    break;
                }
            }
        }

    }
}

