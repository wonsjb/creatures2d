package net.barbux.creatures2d;

import java.util.*;

public class Mutator {

    public boolean mutate(Random random, Creature creature) {
        // 1% chance new node
        if (random.nextDouble() < 0.05) {
            Creature.Node newNode = new Creature.Node(creature.allNodes.size(), random.nextDouble(), random.nextDouble());
            creature.addRandomConnection(newNode, creature.allNodes.get(random.nextInt(creature.allNodes.size())), random);
            creature.allNodes.add(newNode);
        }

        // 1% change lose node
        if (random.nextDouble() < 0.05) {
            Creature.Node removal = creature.allNodes.remove(random.nextInt(creature.allNodes.size()));
            creature.allBones.removeIf(bone -> bone.node1 == removal || bone.node2 == removal);
            creature.allMuscles.removeIf(muscle -> muscle.node1 == removal || muscle.node2 == removal);
        }

        // all muscle have a 20% change of changing period and length by up to 10%
        // 1% change becoming a bone
        // 1% change diseapearing
        for (Iterator<Creature.Muscle> iterator = creature.allMuscles.iterator(); iterator.hasNext();) {
            Creature.Muscle muscle = iterator.next();
            if (random.nextDouble() < 0.02) {
                if (random.nextDouble() < 0.5) {
                    creature.allBones.add(new Creature.Bone(muscle.node1, muscle.node2));
                }
                iterator.remove();
            } else {
                if (random.nextDouble() < 0.2) {
                    muscle.period += Math.max(0.1, (random.nextDouble() - 0.5) * 2 * muscle.period * 0.1);
                }
                if (random.nextDouble() < 0.2) {
                    muscle.minLength += (random.nextDouble() - 0.5) * 2 * muscle.minLength * 0.1;
                    muscle.minLength = Math.max(0, muscle.minLength);
                    muscle.minLength = Math.min(1.0, muscle.minLength);
                }
                if (random.nextDouble() < 0.2) {
                    muscle.maxLength += (random.nextDouble() - 0.5) * 2 * muscle.maxLength * 0.1;
                    muscle.maxLength = Math.max(muscle.minLength, muscle.maxLength);
                    muscle.maxLength = Math.min(1.0, muscle.maxLength);
                }
            }
        }

        // 1% change of new connection
        if (random.nextDouble() < 0.01) {
            int node1index = random.nextInt(creature.allNodes.size());
            int node2index = random.nextInt(creature.allNodes.size());
            if (node1index == node2index) {
                node2index = (node2index + 1) % creature.allNodes.size();
            }
            Creature.Node node1 = creature.allNodes.get(node1index);
            Creature.Node node2 = creature.allNodes.get(node2index);
            boolean alreadyConnected = isAlreadyConnected(creature, node1, node2);
            if (!alreadyConnected) {
                creature.addRandomConnection(node1, node2, random);
            }
        }

        // the resulting creature might not be connected
        Map<Creature.Node, Component> connectedComponenents = new HashMap<>(creature.allNodes.size());
        Set<Component> allComponents = new HashSet<>();
        for (Creature.Node node : creature.allNodes) {
            Component component = new Component();
            component.nodeSet.add(node);
            connectedComponenents.put(node, component);
            allComponents.add(component);
        }
        gatherConnections(connectedComponenents, allComponents, creature.allBones);
        gatherConnections(connectedComponenents, allComponents, creature.allMuscles);
        return allComponents.size() == 1;
    }

    private void gatherConnections(Map<Creature.Node, Component> connectedComponenents, Set<Component> allComponents, List<? extends Creature.Connection> connections) {
        for (Creature.Connection connection : connections) {
            Component component1 = connectedComponenents.get(connection.node1);
            Component component2 = connectedComponenents.get(connection.node2);
            if (component1 != component2) {
                component2.nodeSet.addAll(component1.nodeSet);
                for (Creature.Node node : component1.nodeSet) {
                    connectedComponenents.put(node, component2);
                }
                allComponents.remove(component1);
            }
        }
    }

    private static class Component
    {
        Set<Creature.Node> nodeSet = new HashSet<>();
    }

    private boolean isAlreadyConnected(Creature creature, Creature.Node node1, Creature.Node node2) {
        boolean alreadyConnected = false;
        for (Creature.Bone bone : creature.allBones) {
            if (bone.node1 == node1 && bone.node2 == node2 || bone.node2 == node1 && bone.node1 == node2) {
                alreadyConnected = true;
                break;
            }
        }
        if (!alreadyConnected) {
            for (Creature.Muscle muscle : creature.allMuscles) {
                if (muscle.node1 == node1 && muscle.node2 == node2 || muscle.node2 == node1 && muscle.node1 == node2) {
                    alreadyConnected = true;
                    break;
                }
            }
        }
        return alreadyConnected;
    }
}
