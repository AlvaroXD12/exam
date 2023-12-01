package com.upeu.exam.controller;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import static com.upeu.exam.commons.GlobalConstans.API_CLIENTE;

import com.upeu.exam.entity.ClienteEntity;
import com.upeu.exam.serviceImpl.ClienteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_CLIENTE)
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8081/cliente"})
public class ClienteController {
	@Autowired
	private ClienteServiceImpl clienteServiceImpl ;

	@GetMapping("/all")
	public List<ClienteEntity> listar() {
		return clienteServiceImpl.readAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteEntity> getAutorById(@PathVariable("id") Long id){
		Optional<ClienteEntity> cliData = clienteServiceImpl.read(id);
	    if (cliData.isPresent()) {
	      return new ResponseEntity<ClienteEntity>(cliData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/add")
	public ResponseEntity<ClienteEntity> createAlumno(@Valid @RequestBody ClienteEntity c) {
		try {
			ClienteEntity cli = clienteServiceImpl .create(c);
			return new ResponseEntity<>(cli, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<ClienteEntity> updateBus(@PathVariable("id") long id, @Valid @RequestBody ClienteEntity cli) {
		Optional<ClienteEntity> cliData = clienteServiceImpl.read(id);

		if (cliData.isPresent()) {
			ClienteEntity dbcli = cliData.get();
			dbcli.setNif(cli.getNif());
			dbcli.setNombres(cli.getNombres());
			dbcli.setApellidos(cli.getApellidos());
			dbcli.setTelefono(cli.getTelefono());
			dbcli.setEmail(cli.getEmail());
			
			return new ResponseEntity<ClienteEntity>(clienteServiceImpl.update(dbcli), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ClienteEntity> delete(@PathVariable("id") Long id){
		try {
			clienteServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
}
