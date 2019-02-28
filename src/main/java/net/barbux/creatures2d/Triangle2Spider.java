package net.barbux.creatures2d;

public class Triangle2Spider extends Creature {

    public Triangle2Spider() {
        Node node1 = new Node(0, 0.8, 0.55);
        Node node2 = new Node(1, 1, 0.75);
        Node node3 = new Node(2, 1.2, 0.65);
        Node node4 = new Node(3, 1.4, 0.75);
        Node node5 = new Node(4, 1.6, 0.55);


        allNodes.add(node1);
        allNodes.add(node2);
        allNodes.add(node3);
        allNodes.add(node4);
        allNodes.add(node5);

        allBones.add(new Bone(node1, node2));
        allBones.add(new Bone(node2, node3));
        allBones.add(new Bone(node1, node3));
        allBones.add(new Bone(node3, node4));
        allBones.add(new Bone(node4, node5));
        allBones.add(new Bone(node3, node5));

        allMuscles.add(new Muscle(node2, node4, 0.2, 0.45, 0.5, 250));
    }
}
