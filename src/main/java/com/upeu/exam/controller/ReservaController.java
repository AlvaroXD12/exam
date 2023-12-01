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



import static com.upeu.exam.commons.GlobalConstans.API_RESERVA;

import com.upeu.exam.entity.ReservaEntity;
import com.upeu.exam.serviceImpl.ReservaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_RESERVA)
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8081/reserva"})
public class ReservaController {
	@Autowired
	private ReservaServiceImpl reservaServiceImpl ;

	@GetMapping("/all")
	public ResponseEntity<List<ReservaEntity>> listar() {
	    try {
	        List<ReservaEntity> reserva = reservaServiceImpl.readAll();
	        if (reserva.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        }
	        return ResponseEntity.ok(reserva);
	    } catch (Exception e) {
	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReservaEntity> getAutorById(@PathVariable("id") Long id){
		Optional<ReservaEntity> resData = reservaServiceImpl.read(id);
	    if (resData.isPresent()) {
	      return new ResponseEntity<ReservaEntity>(resData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/add")
	public ResponseEntity<ReservaEntity> crear(@Valid @RequestBody ReservaEntity r) {
		try {
			ReservaEntity res = reservaServiceImpl .create(r);
			return new ResponseEntity<>(res, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<ReservaEntity> updateBus(@PathVariable("id") long id, @Valid @RequestBody ReservaEntity res) {
		Optional<ReservaEntity> resData = reservaServiceImpl.read(id);

		if (resData.isPresent()) {
			ReservaEntity dbres = resData.get();
			dbres.setClase(res.getClase());
			dbres.setVuelo(res.getVuelo());
			dbres.setCliente(res.getCliente());
			dbres.setHotel(res.getHotel());
			dbres.setSucursal(res.getSucursal());
			
			
			return new ResponseEntity<ReservaEntity>(reservaServiceImpl.update(dbres), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ReservaEntity> delete(@PathVariable("id") Long id){
		try {
			reservaServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
}
