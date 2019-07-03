package rva.reps;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Racun;
import rva.jpa.StavkaRacuna;

public interface StavkaRacunaRepository extends JpaRepository<StavkaRacuna, Integer>{

	Collection<StavkaRacuna> findByRacun(Racun r);
	Collection<StavkaRacuna> findByCenaLessThanOrderById(BigDecimal cena);
}
