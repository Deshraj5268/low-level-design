package elevator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Floor {
    private int id;

    public Floor(){

    }
    public Floor(int id){
        this.id = id;
    }
}
