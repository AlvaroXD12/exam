package com.upeu.exam.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.upeu.exam.entity.ReservaEntity;
import com.upeu.exam.repository.ReservaRepository;
import com.upeu.exam.service.Operaciones;
@Service
public class ReservaServiceImpl implements Operaciones<ReservaEntity>{
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public ReservaEntity create(ReservaEntity t) {
		// TODO Auto-generated method stub
		return reservaRepository.save(t);
	}

	@Override
	public ReservaEntity update(ReservaEntity t) {
		// TODO Auto-generated method stub
		return reservaRepository.save(t);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		reservaRepository.deleteById(id);
		
	}

	@Override
	public Optional<ReservaEntity> read(Long id) {
		// TODO Auto-generated method stub
		return reservaRepository.findById(id);
	}

	@Override
	public List<ReservaEntity> readAll() {
		// TODO Auto-generated method stub
		return reservaRepository.findAll();
	}

	
}