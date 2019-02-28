package net.barbux.creatures2d;

import java.io.Serializable;

public interface Physics extends Serializable {

    double EPSILON = 1e-6;
    double g = 9.8;
    double airLamda = 0.01;

    void update(long nanos, Creature creature);

}
