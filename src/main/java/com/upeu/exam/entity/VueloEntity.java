package com.upeu.exam.entity;

import java.sql.Date;
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
@Table(name="VUELO")
public class VueloEntity {
	@Id
	@Column(name = "ID_VUELO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVuelo")
    @SequenceGenerator(name = "seqVuelo", allocationSize = 1, sequenceName = "SEQ_VUELO")
	@Builder.Default
    private Long id =0L;
	
	@Column(name = "FECHA_SALIDA")
	@NotNull @NotBlank  
    private Date fecha_salida;
	
	@Column(name = "HORA_SALIDA")
	@NotNull @NotBlank 
    private Date hora_salida;
	
	@Column(name = "FECHA_LLEGADA")
	@NotNull @NotBlank 
    private Date fecha_llegada;
	
	@Column(name = "HORA_LLEGADA")
	@NotNull @NotBlank 
    private Date hora_llegada;
	
	@Column(name = "ORIGEN")
	@NotNull @NotBlank 
    private String origen;
	
	@Column(name = "DESTINO")
	@NotNull @NotBlank 
    private String destino;
	
	@Column(name = "NUMERO_PLAZAS_TOTALES")
	@NotNull @NotBlank 
    private Integer numero_plazas_totales;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vuelo")
	@JsonIgnore
	private Set<ReservaEntity> reserva;
	
	
}