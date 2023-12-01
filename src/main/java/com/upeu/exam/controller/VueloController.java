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



import static com.upeu.exam.commons.GlobalConstans.API_VUELO;

import com.upeu.exam.entity.VueloEntity;
import com.upeu.exam.serviceImpl.VueloServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_VUELO)
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8081/vuelo"})
public class VueloController {
	@Autowired
	private VueloServiceImpl vueloServiceImpl ;

	@GetMapping("/all")
	public List<VueloEntity> listar() {
		return vueloServiceImpl.readAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<VueloEntity> getAutorById(@PathVariable("id") Long id){
		Optional<VueloEntity> vueData = vueloServiceImpl.read(id);
	    if (vueData.isPresent()) {
	      return new ResponseEntity<VueloEntity>(vueData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/add")
	public ResponseEntity<VueloEntity> createAlumno(@Valid @RequestBody VueloEntity v) {
		try {
			VueloEntity vue = vueloServiceImpl .create(v);
			return new ResponseEntity<>(vue, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<VueloEntity> updateBus(@PathVariable("id") long id, @Valid @RequestBody VueloEntity vue) {
		Optional<VueloEntity> vueData = vueloServiceImpl.read(id);

		if (vueData.isPresent()) {
			VueloEntity dbvue = vueData.get();
			dbvue.setFecha_salida(vue.getFecha_salida());
			dbvue.setHora_salida(vue.getHora_salida());
			dbvue.setFecha_llegada(vue.getFecha_llegada());
			dbvue.setHora_llegada(vue.getHora_llegada());
			dbvue.setOrigen(vue.getOrigen());
			dbvue.setDestino(vue.getDestino());
			dbvue.setNumero_plazas_totales(vue.getNumero_plazas_totales());
						
			
			return new ResponseEntity<VueloEntity>(vueloServiceImpl.update(dbvue), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<VueloEntity> delete(@PathVariable("id") Long id){
		try {
			vueloServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
}
