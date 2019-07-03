package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Proizvod;
import rva.reps.ProizvodRepository;

@Api(tags = {"Proizvod CRUD operacije"})
@RestController
public class ProizvodRestController {

	@Autowired
	private ProizvodRepository proizvodRepository;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Vraca kolekciju svih proizvoda iz baze podataka")
	@GetMapping("proizvod")
	public Collection<Proizvod> getProizvodi() {
		return proizvodRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca proizvod iz baze podataka ciji je id vrednost prosledena kao path varijabla")
	@GetMapping("proizvod/{id}")
	public Proizvod getProizvod(@PathVariable("id") Integer id) {
		return proizvodRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraca kolekciju svih proizvoda iz baze podataka koji u nazivu sadrže string prosleden kao path varijabla")
	@GetMapping("proizvodNaziv/{naziv}")
	public Collection<Proizvod> getProizvodByNaziv(@PathVariable ("naziv") String naziv) {
		return proizvodRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Briše proizvod iz baze podataka ciji je id vrednost prosledena kao path varijabla")
	@CrossOrigin
	@DeleteMapping("proizvod/{id}")
	public ResponseEntity<Proizvod> deleteProizvod(@PathVariable ("id") Integer id) {
		if (!proizvodRepository.existsById(id)) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		proizvodRepository.deleteById(id);
		if(id == -100)
			jdbcTemplate.execute(" INSERT INTO \"proizvod\" (\"id\", \"naziv\", \"proizvodjac\") VALUES (-100, 'Naziv Test', 'Proizvodjac Test') ");
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
	// insert
	@CrossOrigin
	@PostMapping("proizvod")
	@ApiOperation(value = "Upisuje prozivod u bazu podataka")
	public ResponseEntity<Proizvod> insertProizvod(@RequestBody Proizvod proizvod) {
		if (!proizvodRepository.existsById(proizvod.getId())) {
			proizvodRepository.save(proizvod);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	// update
	@CrossOrigin
	@PutMapping("proizvod")
	@ApiOperation(value = "Modifikuje postojeci proizvod u bazi podataka")
	public ResponseEntity<Proizvod> updateProizvod(@RequestBody Proizvod proizvod) {
		if (!proizvodRepository.existsById(proizvod.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		proizvodRepository.save(proizvod);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

