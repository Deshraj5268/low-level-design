package battleshipgame.service;

import battleshipgame.model.Coordinate;

public interface FireStrategy {

    Coordinate getNextTarget();
}
