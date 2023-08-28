package vehiclerentalsystem.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class Location {
    private int id;
    private String country;
    private String state;
    private String address;
    private int pinCode;
}
