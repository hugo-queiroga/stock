package br.com.queiroga.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@SequenceGenerator(name="beverage_type_seq", initialValue=1,allocationSize = 1)
public class BeverageType {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beverage_type_seq")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false)
	private Integer maxVolume;
	
}
