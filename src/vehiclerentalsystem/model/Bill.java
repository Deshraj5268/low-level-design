package vehiclerentalsystem.model;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

    private int reservationId;
    private BigDecimal amount;
    private boolean isPaid;
}
