package com.upeu.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.upeu.exam.entity.HotelEntity;


public interface HotelRepository extends JpaRepository<HotelEntity, Long>{

}