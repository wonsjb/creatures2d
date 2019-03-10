package net.barbux.creatures2d;

public interface Physics {

    double EPSILON = 1e-6;
    double g = 9.8;
    double airLamda = 0.01;

    void update(long nanos);

    void initialize(Creature creature);
}
