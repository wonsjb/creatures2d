package net.barbux.creatures2d;

import java.util.*;

public class RandomCreature extends Creature {

    final Random random;

    static class PairOfNode {
        final int node1;
        final int node2;

        PairOfNode(int node1, int node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PairOfNode that = (PairOfNode) o;
            return node1 == that.node1 &&
                    node2 == that.node2;
        }

        @Override
        public int hashCode() {

            return Objects.hash(node1, node2);
        }
    }

    RandomCreature(Random random) {
        this.random = random;
        int numberOfNode = 3 + random.nextInt(8);
        for (int i = 0; i < numberOfNode; ++i) {
            Node node = new Node(i, random.nextDouble(), random.nextDouble());
            allNodes.add(node);
        }
        List<PairOfNode> allPairs = new ArrayList<>((numberOfNode - 1) * (numberOfNode - 2));
        for (int i = 0; i < numberOfNode; ++i) {
            for (int j = i + 2; j < numberOfNode; ++j) {
                allPairs.add(new PairOfNode(i, j));
            }
        }

        // make sure the creature is fully connected
        for (int i = 0; i < numberOfNode - 1; ++i) {
            addRandomConnection(allNodes.get(i), allNodes.get(i + 1), random);
        }

        // Full connectedness is n * n-1 / 2 connection, and we already did n - 1
        // so remaining possibilities: n-1 * n-2 / 2
        int extraConnections = random.nextInt(allPairs.size());
        Collections.shuffle(allPairs, random);

        for (int i = 0; i < extraConnections; ++i) {
            PairOfNode pair = allPairs.get(i);
            addRandomConnection(allNodes.get(pair.node1), allNodes.get(pair.node2), random);
        }

    }
}
