package vehiclerentalsystem.model;

import lombok.*;
import vehiclerentalsystem.enums.ReservationStatus;
import vehiclerentalsystem.enums.VehicleType;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private int id;
    private String vehicleNUmber;
    private VehicleType vehicleType;
    private ReservationStatus status;
    private String companyName;
}
