package vehiclerentalsystem.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private int id;
    private Vehicle vehicle;
    private Date bookingStartDate;
    private Date bookingTillDate;
    private Location pickup;
    private Location drop;

}
