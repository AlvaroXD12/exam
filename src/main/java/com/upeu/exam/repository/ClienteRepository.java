package com.upeu.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.upeu.exam.entity.ClienteEntity;


public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>{

}