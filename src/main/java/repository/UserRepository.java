package repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import domain.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {
	Optional<Users> findByUsername(String username);
	Optional<Users> findByUsernameAndPassword(String username, String password);
}
