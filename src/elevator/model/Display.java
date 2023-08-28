package elevator.model;

import elevator.enums.Direction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Display {

    private Floor floor;
    private Direction direction;

    public Display(){

    }

    public Display(Floor floor,Direction direction){
        this.floor = floor;
        this.direction = direction;
    }
}
