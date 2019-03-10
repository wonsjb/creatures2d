package net.barbux.creatures2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.barbux.creatures2d.proto.Creatures;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;


public class Creature {

    static class Node {
        final Geometry.Vector originalPos;
        final Geometry.Vector p;
        final Geometry.Vector v;
        int nodeId;

        final double size;
        final double weight;

        Color color = Color.BROWN;

        Node(double x, double y) {
            this(x, y, 1.0, 0.05);
        }

        Node(double x, double y, double weight, double size) {
            p = new Geometry.Vector(x, y);
            originalPos = new Geometry.Vector(x, y);
            v = new Geometry.Vector(0d, 0d);
            this.weight = weight;
            this.size = size;
        }

        public Node clone() {
            Node result = new Node(originalPos.x, originalPos.y, weight, size);
            result.color = color;
            return result;
        }
    }

    static abstract class Connection {
        final Node node1;
        final Node node2;

        Connection(Node node1, Node node2) {
            this.node1 = node1;
            this.node2 = node2;
        }

        double expectedLength;

        abstract void setExpectedLength(double time);
    }

    static class Bone extends Connection {
        final double length;
        Color color = Color.BLACK;

        Bone(Node node1, Node node2) {
            this(node1, node2, Math.sqrt(Geometry.distance2(node1.p, node2.p)));
        }

        @Override
        void setExpectedLength(double time) {
            expectedLength = length;
        }

        Bone(Node node1, Node node2, double length) {
            super(node1, node2);
            this.length = length;
        }

        public Bone clone() {
            return new Bone(node1, node2, length);
        }
    }

    static class Muscle extends Connection {

        double minLength; // m
        double maxLength; // m

        double period; // s
        double power; // W


        Muscle(Node node1, Node node2, double minLength, double maxLength, double period, double power) {
            super(node1, node2);
            this.maxLength = maxLength;
            this.minLength = minLength;
            this.period = period;
            this.power = power;
        }

        Muscle(Node node1, Node node2, double lengthChange, double period, double power) {
            super(node1, node2);
            double distance = Math.sqrt(Geometry.distance2(node1.p, node2.p));
            this.maxLength = distance + lengthChange / 2;
            this.minLength = Math.max(distance - lengthChange / 2, 0);
            this.period = period;
            this.power = power;
        }

        @Override
        void setExpectedLength(double time) {
            expectedLength = Math.sin(time * 2 * Math.PI / period) * (maxLength - minLength) * 0.5 + 1.5 * minLength;
        }
    }

    class NodeList implements Iterable<Node> {

        private ArrayList<Node> internal = new ArrayList<>();

        public boolean add(Node node) {
            node.nodeId = internal.size();
            return internal.add(node);
        }

        public Node remove(int index) {
            Node removal = internal.remove(index);
            allBones.removeIf(bone -> bone.node1 == removal || bone.node2 == removal);
            allMuscles.removeIf(muscle -> muscle.node1 == removal || muscle.node2 == removal);
            for (int i = index; i < internal.size(); ++i) {
                internal.get(i).nodeId = i;
            }
            return removal;
        }

        public boolean removeIf(Predicate<? super Node> predicate) {
            boolean res = internal.removeIf(predicate);
            allBones.removeIf(bone -> predicate.test(bone.node1) || predicate.test(bone.node2));
            allMuscles.removeIf(muscle -> predicate.test(muscle.node1) || predicate.test(muscle.node2));
            return res;
        }

        public int size() {
            return internal.size();
        }

        public Node get(int i) {
            return internal.get(i);
        }

        public Stream<Node> stream() {
            return internal.stream();
        }

        @Override
        public Iterator<Node> iterator() {
            return internal.iterator();
        }
    }

    final NodeList allNodes = new NodeList();
    final List<Bone> allBones = new ArrayList<>();
    final List<Muscle> allMuscles = new ArrayList<>();
    int creatureId;

    Physics physics;

    Creature() {
    }

    void initializePhysics(Supplier<Physics> physicsSupplier) {
        physics = physicsSupplier.get();
        physics.initialize(this);
    }

    void resizeBones() {
        for (int i = 0; i < allBones.size(); ++i) {
            Bone bone = allBones.get(i);
            Bone newBone = new Bone(bone.node1, bone.node2);
            allBones.set(i, newBone);
        }
    }

    void update(long nanos) {
        physics.update(nanos);
        getFitness();
    }

    double maxReached = 0.0;

    double getFitness() {
        for (Node node : allNodes) {
            if (node.p.x > maxReached) {
                maxReached = node.p.x;
            }
        }
        return maxReached;
    }

    Geometry.Vector getCenter() {
        double minx = Double.MAX_VALUE;
        double maxx = Double.MIN_VALUE;

        double miny = Double.MAX_VALUE;
        double maxy = Double.MIN_VALUE;

        for (Node node : allNodes) {
            Geometry.Vector p = node.p;
            if (p.x < minx) {
                minx = p.x;
            }
            if (p.x > maxx) {
                maxx = p.x;
            }
            if (p.y < miny) {
                miny = p.y;
            }
            if (p.y > maxy) {
                maxy = p.y;
            }
        }
        return new Geometry.Vector((maxx + minx) / 2, (maxy + miny) / 2);
    }

    void render(GraphicsContext gc, boolean showNumber) {
        gc.setLineWidth(0.01);
        for (Bone bone : allBones) {
            gc.setStroke(bone.color);
            gc.strokeLine(bone.node1.p.x, bone.node1.p.y, bone.node2.p.x, bone.node2.p.y);
        }

        gc.setStroke(Color.RED);
        gc.setLineWidth(0.02);
        for (Muscle muscle : allMuscles) {
            gc.strokeLine(muscle.node1.p.x, muscle.node1.p.y, muscle.node2.p.x, muscle.node2.p.y);
        }

        for (Node node : allNodes) {
            gc.setFill(node.color);
            gc.fillOval(node.p.x - node.size / 2, node.p.y - node.size / 2, node.size, node.size);
        }

        if (showNumber) {
            gc.save();
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font(10));
            gc.scale(0.01, -0.01);
            gc.fillText(Integer.toString(creatureId), 0, -10);
            gc.restore();
        }

    }

    public Creature clone() {
        Creature creature = new Creature();
        Map<Node, Node> oldToNew = new HashMap<>();
        for (Node oldNode : allNodes) {
            Node newNode = oldNode.clone();
            oldToNew.put(oldNode, newNode);
            creature.allNodes.add(newNode);
        }
        for (Muscle muscle : allMuscles) {
            creature.allMuscles.add(new Muscle(oldToNew.get(muscle.node1), oldToNew.get(muscle.node2), muscle.minLength, muscle.maxLength, muscle.period, muscle.power));
        }
        for (Bone bone : allBones) {
            creature.allBones.add(new Bone(oldToNew.get(bone.node1), oldToNew.get(bone.node2), bone.length));
        }
        return creature;
    }

    Creatures.Creature serialize() {
        Creatures.Creature.Builder builder = Creatures.Creature.newBuilder();
        builder.setCreatureId(creatureId);
        for (Node node : allNodes) {
            Creatures.Node.Builder nodeBuilder = Creatures.Node.newBuilder();
            nodeBuilder.setNodeId(node.nodeId);
            nodeBuilder.setSize(node.size);
            nodeBuilder.setWeight(node.weight);
            nodeBuilder.setStartX(node.originalPos.x);
            nodeBuilder.setStartY(node.originalPos.y);
            builder.addNodes(nodeBuilder);
        }
        for(Bone bone : allBones) {
            Creatures.Bone.Builder boneBuilder = Creatures.Bone.newBuilder();
            boneBuilder.setNode1(bone.node1.nodeId);
            boneBuilder.setNode2(bone.node2.nodeId);
            boneBuilder.setLength(bone.length);
            builder.addBones(boneBuilder);
        }
        for (Muscle muscle : allMuscles) {
            Creatures.Muscle.Builder muscleBuilder = Creatures.Muscle.newBuilder();
            muscleBuilder.setMinLength(muscle.minLength);
            muscleBuilder.setMaxLength(muscle.maxLength);
            muscleBuilder.setNode1(muscle.node1.nodeId);
            muscleBuilder.setNode2(muscle.node2.nodeId);
            muscleBuilder.setPeriod(muscle.period);
            muscleBuilder.setPower(muscle.power);
            builder.addMuscles(muscleBuilder);
        }
        return builder.build();
    }

    static Creature deserialize(Creatures.Creature protoCreature) {
        Creature creature = new Creature();
        creature.creatureId = protoCreature.getCreatureId();
        Map<Integer, Node> allNodes = new HashMap<>();
        for (Creatures.Node protoNode : protoCreature.getNodesList()) {
            Node node = new Node(protoNode.getStartX(), protoNode.getStartY(), protoNode.getWeight(), protoNode.getSize());
            allNodes.put(protoNode.getNodeId(), node);
            creature.allNodes.add(node);
        }
        for (Creatures.Bone protoBone : protoCreature.getBonesList()) {
            Bone bone = new Bone(allNodes.get(protoBone.getNode1()), allNodes.get(protoBone.getNode2()), protoBone.getLength());
            creature.allBones.add(bone);
        }
        for (Creatures.Muscle protoMuscle : protoCreature.getMusclesList()) {
            Muscle muscle = new Muscle(allNodes.get(protoMuscle.getNode1()), allNodes.get(protoMuscle.getNode2()),
                    protoMuscle.getMinLength(), protoMuscle.getMaxLength(), protoMuscle.getPeriod(), protoMuscle.getPower());
            creature.allMuscles.add(muscle);
        }
        return creature;
    }

    void addRandomConnection(Node node1, Node node2, Random random) {
        if (random.nextDouble() > 0.2) {
            Bone bone = new Bone(node1, node2);
            allBones.add(bone);
        } else {
            Muscle muscle = new Muscle(node1, node2, random.nextDouble() * 0.3, 0.2 + random.nextDouble() * 0.8, 250);
            allMuscles.add(muscle);
        }
    }

    public boolean mutate(Random random) {
        return new Mutator().mutate(random, this);
    }


}
