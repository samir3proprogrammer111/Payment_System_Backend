package Banking_Management_System.Acc.Controller;

import Banking_Management_System.Acc.DTO.AccountDTO;
import Banking_Management_System.Acc.DTO.TransferDTO;
import Banking_Management_System.Acc.Service.AccountService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/email")
    public AccountDTO getAccountByEmail(@RequestParam String email) {
        return accountService.getAccountByEmail(email);
    }

    @GetMapping("/name")
    public AccountDTO findByName(@RequestParam String name) {
        return accountService.findByName(name);
    }

    @GetMapping("/account-number")
    public AccountDTO findByAccountNumber(
            @RequestParam String accountNumber
    ) {
        return accountService.findByAccountNumber(accountNumber);
    }

    @PutMapping("/{id}")
    public AccountDTO updateAccount(
            @PathVariable Long id,
            @RequestBody AccountDTO accountDTO
    ) {
        return accountService.updateAccount(accountDTO, id);
    }

    @PutMapping("/{id}/deposit")
    public AccountDTO deposit(
            @PathVariable Long id,
            @RequestParam Double amount
    ) {
        return accountService.deposit(id, amount);
    }

    @PutMapping("/{id}/withdraw")
    public AccountDTO withdraw(
            @PathVariable Long id,
            @RequestParam Double amount
    ) {
        return accountService.withdraw(id, amount);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return "Account deleted successfully";
    }

    @PostMapping("/transfer")
    public String TransferMoney(
            @RequestBody TransferDTO request
    ) {
        return  accountService.TransferAmount(request);
    }
}