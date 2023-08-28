package elevator.service;

import elevator.enums.Direction;
import elevator.model.ElevatorCar;
import elevator.model.Floor;

public class ElevatorService {

    ElevatorCar elevatorCar;
    ElevatorStrategyAlgorithm elevatorStrategyAlgorithm;

    public void submitRequest(Floor floor, Direction direction){
        switch (direction) {
            case DOWN:
                elevatorStrategyAlgorithm.pendingFloorForDownDirection(floor);
                break;
            case UP:
                elevatorStrategyAlgorithm.pendingFloorForUpDirection(floor);
        }
    }
}
