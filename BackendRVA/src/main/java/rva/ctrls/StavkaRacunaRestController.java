package rva.ctrls;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.jpa.Racun;
import rva.jpa.StavkaRacuna;
import rva.reps.RacunRepository;
import rva.reps.StavkaRacunaRepository;

@RestController
public class StavkaRacunaRestController {
	
	@Autowired
	private StavkaRacunaRepository stavkaRacunaRepository;
	@Autowired
	private RacunRepository racunRepository;

	@GetMapping(value = "stavkaRacuna")
	public Collection<StavkaRacuna> getStavkeRacuna(){
		return stavkaRacunaRepository.findAll();
	}

	@GetMapping(value = "stavkaRacuna/{id}")
	public ResponseEntity<StavkaRacuna> getStavkaRacuna(@PathVariable("id") Integer id){
		StavkaRacuna stavkaRacuna = stavkaRacunaRepository.getOne(id);
		return new ResponseEntity<StavkaRacuna>(stavkaRacuna, HttpStatus.OK);
	}

	@GetMapping(value = "stavkeZaRacunId/{id}")
	public Collection<StavkaRacuna> stavkaPoRacunuId(@PathVariable("id") int id){
		Racun r = racunRepository.getOne(id);
		return stavkaRacunaRepository.findByRacun(r);
	}
	
	@GetMapping(value = "stavkaRacunaCena/{cena}")
	public Collection<StavkaRacuna> getStavkaRacunaCena(@PathVariable("cena") BigDecimal cena){
		return stavkaRacunaRepository.findByCenaLessThanOrderById(cena);
	}

	@CrossOrigin
	@DeleteMapping (value = "stavkaRacuna/{id}")
	public ResponseEntity<StavkaRacuna> deleteStavkaRacuna(@PathVariable("id") Integer id){
		if(!stavkaRacunaRepository.existsById(id))
			return new ResponseEntity<StavkaRacuna>(HttpStatus.NO_CONTENT);
		stavkaRacunaRepository.deleteById(id);
		return new ResponseEntity<StavkaRacuna>(HttpStatus.OK);
	}

	//insert
	@CrossOrigin
	@PostMapping(value = "stavkaRacuna")
	public ResponseEntity<Void> insertStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna){
		if(stavkaRacunaRepository.existsById(stavkaRacuna.getId()))
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		stavkaRacunaRepository.save(stavkaRacuna);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//update
	@CrossOrigin
	@PutMapping(value = "stavkaRacuna")
	public ResponseEntity<Void> updateStavkaRacuna(@RequestBody StavkaRacuna stavkaRacuna){
		if(!stavkaRacunaRepository.existsById(stavkaRacuna.getId()))
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		stavkaRacunaRepository.save(stavkaRacuna);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}