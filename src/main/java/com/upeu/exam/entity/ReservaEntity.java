package com.upeu.exam.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="RESERVA")
public class ReservaEntity {
	@Id
	@Column(name = "ID_RESERVA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqReserva")
    @SequenceGenerator(name = "seqReserva", allocationSize = 1, sequenceName = "SEQ_RESERVA")
	@Builder.Default
    private Long id =0L;
	
	@Column(name = "CLASE")
	@NotNull @NotBlank  
    private char clase;
	
	@ManyToOne
    @JoinColumn(name="ID_CLIENTE", nullable = false)
    private ClienteEntity cliente;
	
	@ManyToOne
    @JoinColumn(name="ID_HOTEL", nullable = false)
    private HotelEntity hotel;
	
	@ManyToOne
    @JoinColumn(name="ID_SUCURSAL", nullable = false)
    private SucursalEntity sucursal;
	
	@ManyToOne
    @JoinColumn(name="ID_VUELO", nullable = false)
    private VueloEntity vuelo;

}