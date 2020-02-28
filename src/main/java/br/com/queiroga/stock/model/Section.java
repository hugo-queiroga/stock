package br.com.queiroga.stock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name="section_seq", initialValue=1,allocationSize = 1)
public class Section {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_seq")
	private Long id;
	
	@Column(nullable = false,unique = true)
	private String name;
	
	@Column(nullable = false)
	private Integer spaceUsed;
	
	@ManyToOne
	private BeverageType storedType;
	
}
