package repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import domain.Boek;
import domain.BoekLocatie;

public interface BoekLocatieRespository extends CrudRepository<BoekLocatie, Integer> {
    Optional<BoekLocatie> findByLocatieID(Integer locatieID);
    Optional<BoekLocatie> findByPlaatsnaam(String plaatsnaam);
}
