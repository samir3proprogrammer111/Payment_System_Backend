package Banking_Management_System.Acc.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class TransferDTO {
    private Long fromAccountId;
    private Long toAccountId;
    private String fromAccountName;
    private String toAccountName;

    private double amount;
}
