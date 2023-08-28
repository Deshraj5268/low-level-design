package vehiclerentalsystem.service;

import lombok.*;
import vehiclerentalsystem.model.Store;
import vehiclerentalsystem.model.User;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRentalSystem {

    private List<Store> stores;
    private List<User> users;


    // store listing
    // store selection
    // vehicle Inv management

}
