package br.com.queiroga.stock.model.dto;

import br.com.queiroga.stock.model.BeverageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageStockDTO {
	
	private BeverageType type;
	
	private SectionDTO section;

	private Integer volume;

	private String user;
	
}
