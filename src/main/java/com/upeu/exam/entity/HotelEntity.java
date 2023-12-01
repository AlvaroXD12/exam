package com.upeu.exam.entity;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="HOTEL")
public class HotelEntity {
	@Id
	@Column(name = "ID_HOTEL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqHotel")
    @SequenceGenerator(name = "seqHotel", allocationSize = 1, sequenceName = "SEQ_HOTEL")
	@Builder.Default
    private Long id =0L;
	
	@Column(name = "NOMBRE")
	@NotNull @NotBlank  
    private String nombre;
	
	@Column(name = "DIRECCION")
	@NotNull @NotBlank
    private String direccion;
	
	@Column(name = "LOCALIDAD")
	@NotNull @NotBlank
    private String localidad;
	
	@Column(name = "PROVINCIA")
	@NotNull @NotBlank
    private String provincia;
	
	@Column(name = "TELEFONO")
	@NotNull @NotBlank
    private Integer telefono;
	
	@Column(name = "NUMERO_ESTRELLAS")
	@NotNull @NotBlank
    private Integer numero_estrellas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
	@JsonIgnore
	private Set<ReservaEntity> reserva;
	
}