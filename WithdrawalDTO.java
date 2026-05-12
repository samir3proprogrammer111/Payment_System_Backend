package Banking_Management_System.Acc.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WithdrawalDTO {
    private Long id;
    private double amount;

}
