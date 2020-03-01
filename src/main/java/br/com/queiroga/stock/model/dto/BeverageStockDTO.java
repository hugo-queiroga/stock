package br.com.queiroga.stock.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.queiroga.stock.model.enums.BeverageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeverageStockDTO {

	@NotBlank(message = "The description cannot be empty")
	private String description;
	
	@NotNull(message = "The section cannot be null")
	private SectionDTO section;

	@NotNull(message = "The type cannot be null")
	private BeverageTypeEnum type;

	@NotNull(message = "The volume cannot be null")
	private Integer volume;
		
	@NotBlank(message = "The user cannot be null")
	private String user;
	
}
