package com.upeu.exam.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.upeu.exam.entity.VueloEntity;
import com.upeu.exam.repository.VueloRepository;
import com.upeu.exam.service.Operaciones;
@Service
public class VueloServiceImpl implements Operaciones<VueloEntity>{
	
	@Autowired
	private VueloRepository vueloRepository;

	@Override
	public VueloEntity create(VueloEntity t) {
		// TODO Auto-generated method stub
		return vueloRepository.save(t);
	}

	@Override
	public VueloEntity update(VueloEntity t) {
		// TODO Auto-generated method stub
		return vueloRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		vueloRepository.deleteById(id);
		
	}

	@Override
	public Optional<VueloEntity> read(Long id) {
		// TODO Auto-generated method stub
		return vueloRepository.findById(id);
	}

	@Override
	public List<VueloEntity> readAll() {
		// TODO Auto-generated method stub
		return vueloRepository.findAll();
	}

	
}