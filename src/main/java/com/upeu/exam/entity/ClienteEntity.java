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
@Table(name="CLIENTE")
public class ClienteEntity {
	@Id
	@Column(name = "ID_CLIENTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCliente")
    @SequenceGenerator(name = "seqCliente", allocationSize = 1, sequenceName = "SEQ_CLIENTE")
	@Builder.Default
    private Long id =0L;
	
	@Column(name = "NIF")
	@NotNull @NotBlank  
    private Integer nif;
	
	@Column(name = "NOMBRES")
	@NotNull @NotBlank 
    private String nombres;
	
	@Column(name = "APELLIDOS")
	@NotNull @NotBlank 
    private String apellidos;
	
	@Column(name = "TELEFONO")
	@NotNull @NotBlank 
    private Integer telefono;
	
	@Column(name = "EMAIL")
	@NotNull @NotBlank 
    private String email;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	@JsonIgnore
	private Set<ReservaEntity> reserva;
	
	
}