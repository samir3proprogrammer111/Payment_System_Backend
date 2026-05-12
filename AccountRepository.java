package Banking_Management_System.Acc.Repository;


import Banking_Management_System.Acc.Entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional <Account> findByEmail(String email);

    Optional <Account> findByAccountNumber(String accountNumber);

    Optional <Account> findByName(String name);

}
