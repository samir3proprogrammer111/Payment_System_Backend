package Banking_Management_System.Acc.Service;

import Banking_Management_System.Acc.DTO.AccountDTO;
import Banking_Management_System.Acc.DTO.TransferDTO;
import Banking_Management_System.Acc.Entity.Account;
import Banking_Management_System.Acc.Repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO accountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setBalance(account.getBalance());

        return accountDTO;
    }

    /*
     * DTO -> Entity
     */
    public Account account(AccountDTO accountDTO) {
        Account account = new Account();

        account.setId(accountDTO.getId());
        account.setName(accountDTO.getName());
        account.setEmail(accountDTO.getEmail());
        account.setBalance(accountDTO.getBalance());

        return account;
    }

    /*
     * Update Account
     */
    public AccountDTO updateAccount(AccountDTO accountDTO, Long id) {

        Account acc = accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found with id: " + id));

        // Usually do NOT update ID manually
        acc.setName(accountDTO.getName());
        acc.setEmail(accountDTO.getEmail());

        Account updatedAccount = accountRepository.save(acc);

        return accountDTO(updatedAccount);
    }

    /*
     * Deposit Money
     */
    public AccountDTO deposit(Long id, Double amount) {

        Account acc = accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found with id: " + id));

        validateDeposit(amount);
        validateAccountStatus(acc);

        acc.setBalance(acc.getBalance() + amount);

        Account updatedAccount = accountRepository.save(acc);

        return accountDTO(updatedAccount);
    }

    /*
     * Withdraw Money
     */
    public AccountDTO withdraw(Long id, Double amount) {

        Account acc = accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found with id: " + id));

        validateDeposit(amount); // amount must be > 0
        validateWithdrawalLimit(amount);
        validateAccountStatus(acc);
        validateMinimumBalance(acc, amount);


        if (acc.getBalance() < amount) {
            throw new RuntimeException(
                    "Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);

        Account updatedAccount = accountRepository.save(acc);

        return accountDTO(updatedAccount);
    }

    /*
     * Validation Methods
     */

    // Deposit / Withdraw amount must be greater than 0
    private void validateDeposit(Double amount) {
        if (amount <= 0) {
            throw new RuntimeException(
                    "Amount should be greater than 0");
        }
    }

    // Single withdrawal transaction limit
    private void validateWithdrawalLimit(Double amount) {
        if (amount > 10000) {
            throw new RuntimeException(
                    "Amount should not exceed 10000 per transaction");
        }
    }

    // Minimum balance must remain after withdrawal
    private void validateMinimumBalance(Account acc, Double amount) {
        double minimumBalance = 500.0;

        if ((acc.getBalance() - amount) < minimumBalance) {
            throw new RuntimeException(
                    "Minimum balance of 500 must be maintained");
        }
    }

    // Account must be ACTIVE
    // IMPORTANT:
    // Use this only if your Account entity has:
    // private String status;
    private void validateAccountStatus(Account acc) {

        if (acc.getStatus() == null ||
                !acc.getStatus().equalsIgnoreCase("ACTIVE")) {

            throw new RuntimeException(
                    "Account is not active");
        }


    }

    public void deleteAccount(Long id) {
        Account acc  = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found with id: " + id));
        accountRepository.delete(acc);
        Account updatedAccount = accountRepository.save(acc);

        System.out.println("Account has been deleted");

    }

    public AccountDTO  getAccountById(Long id) {
        Account acc   = accountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found with id: " + id));
        return accountDTO(acc);
    }

  public AccountDTO getAccountByEmail(String email) {
        Account acc  = accountRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Account not found with email: " + email));
        return accountDTO(acc);
  }

  public AccountDTO findByName(String name) {
        Account acc  = accountRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException("Account not found with name: " + name));
        return accountDTO(acc);
  }
public AccountDTO findByAccountNumber(String  accountNumber) {
        Account acc  = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()-> new RuntimeException("Account not found with accountNumber: " + accountNumber));
        return accountDTO(acc);
}

@Transactional
    public String TransferAmount(TransferDTO request) {
        Account sender  = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(()-> new RuntimeException("Sender Account Not Found with : " +  request.getFromAccountId()));

                Account receiver  = accountRepository.findById(request.getToAccountId())
                        .orElseThrow(()-> new RuntimeException("Receiver Account not found with : " + request.getToAccountId()));


        if(sender.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");

        }

        if(request.getAmount() <= 0){
            throw new RuntimeException("Minimum amount you can send is 1 rupees");

        }

        sender.setBalance(sender.getBalance() - request.getAmount());
        receiver.setBalance(receiver.getBalance() + request.getAmount());
        accountRepository.save(sender);
        accountRepository.save(receiver);

        return "Transfer Successfully";
}

}

