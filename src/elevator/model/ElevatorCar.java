package elevator.model;

import elevator.enums.Direction;
import elevator.enums.Status;
import elevator.service.ElevatorButton;
import elevator.service.ElevatorDoor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ElevatorCar {

    private int elevatorId;
    private Floor currentFloor;
    private Display display; // button /direction
    private ElevatorButton elevatorButton;
    private ElevatorDoor elevatorDoor;
    private Status status;

    public ElevatorCar(){
        this.status = Status.IDLE;
        this.currentFloor = new Floor(0);
        this.display = new Display(this.currentFloor,Direction.UP);
        this.elevatorDoor = new ElevatorDoor();
    }
}
