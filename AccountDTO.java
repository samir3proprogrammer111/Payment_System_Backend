package Banking_Management_System.Acc.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccountDTO {

    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private double balance;
private  String accountNumber;

























}
