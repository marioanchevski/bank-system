import java.util.Optional;

public class AccountService {

    public Optional<Account> findAccountById(Long id, Bank bank) {
        return bank.getAccounts().stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }

}
