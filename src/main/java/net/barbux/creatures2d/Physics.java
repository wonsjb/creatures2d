package net.barbux.creatures2d;

public interface Physics {

    double EPSILON = 1e-6;
    // Some physics constants
    double g = 9.8;
    double AIR_LAMBDA = 0.01;
    double SOLID_SPRING_LAMBDA = 1_000_000d;
    double FRICTION_CONSTANT = 5d;

    void update(long nanos);

    void initialize(Creature creature);
}
