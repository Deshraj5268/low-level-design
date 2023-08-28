package elevator.service;

import elevator.enums.Direction;
import elevator.model.Floor;

public interface ElevatorButton {

    public void pressButton(Floor floor, Direction direction);

}
