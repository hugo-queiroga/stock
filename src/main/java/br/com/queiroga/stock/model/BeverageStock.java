package br.com.queiroga.stock.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="beverage_stock_seq", initialValue=1,allocationSize = 1)
public class BeverageStock {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beverage_stock_seq" )
	private Long id;
	
	@ManyToOne(optional = false)
	private Section section;
	
	@ManyToOne(optional = false)
	private BeverageType type;
	
	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private Integer volume;
	
	@Column(nullable = false)
	private String user;
	
}
