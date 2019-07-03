package rva.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Proizvodjac;

public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Integer> {
	Collection<Proizvodjac> findByNazivContainingIgnoreCase(String naziv);
}
