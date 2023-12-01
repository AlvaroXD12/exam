package com.upeu.exam.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.upeu.exam.entity.SucursalEntity;
import com.upeu.exam.repository.SucursalRepository;
import com.upeu.exam.service.Operaciones;
@Service
public class SucursalServiceImpl implements Operaciones<SucursalEntity>{
	
	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	public SucursalEntity create(SucursalEntity t) {
		// TODO Auto-generated method stub
		return sucursalRepository.save(t);
	}

	@Override
	public SucursalEntity update(SucursalEntity t) {
		// TODO Auto-generated method stub
		return sucursalRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		sucursalRepository.deleteById(id);
		
	}

	@Override
	public Optional<SucursalEntity> read(Long id) {
		// TODO Auto-generated method stub
		return sucursalRepository.findById(id);
	}

	@Override
	public List<SucursalEntity> readAll() {
		// TODO Auto-generated method stub
		return sucursalRepository.findAll();
	}

	
}