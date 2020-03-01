package br.com.queiroga.stock.model.dto;

import br.com.queiroga.stock.model.Beverage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageStockDTO {
	
	private Beverage type;
	
	private SectionDTO section;

	private Integer volume;

	private String user;
	
}
