package com.upeu.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.upeu.exam.entity.ReservaEntity;


public interface ReservaRepository extends JpaRepository<ReservaEntity, Long>{

}