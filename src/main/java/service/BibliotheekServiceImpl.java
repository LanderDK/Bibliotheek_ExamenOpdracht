package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import repository.BoekRepository;

public class BibliotheekServiceImpl implements BibliotheekService {

//	@Autowired
//	private AccountRepository accountRepository;
	@Autowired
	private BoekRepository boekRepository;

	public void changeWerkruimte(String accountNaam1, String accountNaam2) {

//		Optional<Account> accountA = accountRepository.findByUsername(accountNaam1);
//
//		Optional<Account> accountB = accountRepository.findByUsername(accountNaam2);
//
//		if (accountA.isPresent() && accountB.isPresent()) {
//			
//		}

	}
}
