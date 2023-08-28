package vehiclerentalsystem.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String mobileNUmber;
    private String emailId;
    private String address;
}
