package vehiclerentalsystem.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private int id;
    private Location location;
    List<Reservation> reservationList;
}
