package br.com.queiroga.stock.model.dto;

import br.com.queiroga.stock.model.BeverageType;
import lombok.Data;

@Data
public class BeverageStockDTO {
	
	private BeverageType type;
	
	private SectionDTO section;

	private Integer volume;

	private String user;
	
}
