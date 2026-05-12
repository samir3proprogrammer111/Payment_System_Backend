package Banking_Management_System.Acc.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;

    private double balance;

    private double dailyWithDrawAmount;
    private LocalDate lastWithDrawDate;

    private boolean blocked;

    private String status;
  private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account sender;


    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiver;

}
