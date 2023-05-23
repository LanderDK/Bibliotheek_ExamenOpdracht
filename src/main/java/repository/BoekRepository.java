package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import domain.Boek;

public interface BoekRepository extends CrudRepository<Boek, Integer> {
	Optional<Boek> findByBoekNaam(String boekNaam);
	List<Boek> findAll();
    Optional<Boek> findByBoekID(Integer boekID);
	List<Boek> findMeestPopulair();
    Optional<Boek> findByIsbn(String isbn);
	List<Boek> findAllOfAuteur(String auteur);
}
