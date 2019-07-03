package rva.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Proizvodjac;
import rva.jpa.Racun;

public interface RacunRepository extends JpaRepository<Racun, Integer> {

}
