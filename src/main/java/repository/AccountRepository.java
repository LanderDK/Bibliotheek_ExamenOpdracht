package repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import domain.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
	Optional<Account> findByUsername(String username);
	Optional<Account> findByUsernameAndPassword(String username, String password);
}
