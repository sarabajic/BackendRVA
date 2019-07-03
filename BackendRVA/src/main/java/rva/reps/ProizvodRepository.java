package rva.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Proizvod;

public interface ProizvodRepository extends JpaRepository<Proizvod, Integer> {
	Collection<Proizvod> findByNazivContainingIgnoreCase(String naziv);
}
