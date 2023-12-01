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



import static com.upeu.exam.commons.GlobalConstans.API_HOTEL;

import com.upeu.exam.entity.HotelEntity;
import com.upeu.exam.serviceImpl.HotelServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_HOTEL)
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8081/hotel"})
public class HotelController {
	@Autowired
	private HotelServiceImpl hotelServiceImpl ;

	@GetMapping("/all")
	public List<HotelEntity> listar() {
		return hotelServiceImpl.readAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<HotelEntity> getAutorById(@PathVariable("id") Long id){
		Optional<HotelEntity> hotData = hotelServiceImpl.read(id);
	    if (hotData.isPresent()) {
	      return new ResponseEntity<HotelEntity>(hotData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/add")
	public ResponseEntity<HotelEntity> createAlumno(@Valid @RequestBody HotelEntity h) {
		try {
			HotelEntity hot = hotelServiceImpl .create(h);
			return new ResponseEntity<>(hot, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<HotelEntity> updateBus(@PathVariable("id") long id, @Valid @RequestBody HotelEntity hot) {
		Optional<HotelEntity> hotData = hotelServiceImpl.read(id);

		if (hotData.isPresent()) {
			HotelEntity dbhot = hotData.get();
			dbhot.setNombre(hot.getNombre());
			dbhot.setDireccion(hot.getDireccion());
			dbhot.setLocalidad(hot.getLocalidad());
			dbhot.setProvincia(hot.getProvincia());
			dbhot.setTelefono(hot.getTelefono());
			dbhot.setNumero_estrellas(hot.getNumero_estrellas());
			
			return new ResponseEntity<HotelEntity>(hotelServiceImpl.update(dbhot), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HotelEntity> delete(@PathVariable("id") Long id){
		try {
			hotelServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
}
