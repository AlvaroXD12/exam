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



import static com.upeu.exam.commons.GlobalConstans.API_SUCURSAL;

import com.upeu.exam.entity.SucursalEntity;
import com.upeu.exam.serviceImpl.SucursalServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_SUCURSAL)
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8081/sucursal"})
public class SucursalController {
	@Autowired
	private SucursalServiceImpl sucursalServiceImpl ;

	@GetMapping("/all")
	public List<SucursalEntity> listar() {
		return sucursalServiceImpl.readAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SucursalEntity> getAutorById(@PathVariable("id") Long id){
		Optional<SucursalEntity> sucData = sucursalServiceImpl.read(id);
	    if (sucData.isPresent()) {
	      return new ResponseEntity<SucursalEntity>(sucData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/add")
	public ResponseEntity<SucursalEntity> createAlumno(@Valid @RequestBody SucursalEntity s) {
		try {
			SucursalEntity suc = sucursalServiceImpl .create(s);
			return new ResponseEntity<>(suc, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<SucursalEntity> updateBus(@PathVariable("id") long id, @Valid @RequestBody SucursalEntity suc) {
		Optional<SucursalEntity> sucData = sucursalServiceImpl.read(id);

		if (sucData.isPresent()) {
			SucursalEntity dbsuc = sucData.get();
			dbsuc.setDireccion(suc.getDireccion());
			dbsuc.setLocalidad(suc.getLocalidad());
			dbsuc.setProvincia(suc.getProvincia());
			dbsuc.setTelefono(suc.getTelefono());			
			
			return new ResponseEntity<SucursalEntity>(sucursalServiceImpl.update(dbsuc), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SucursalEntity> delete(@PathVariable("id") Long id){
		try {
			sucursalServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
}
