package br.com.queiroga.stock.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.queiroga.stock.exception.InvalidSectionException;
import br.com.queiroga.stock.model.BeverageStock;
import br.com.queiroga.stock.model.BeverageType;
import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.model.dto.BeverageStockDTO;
import br.com.queiroga.stock.model.dto.SectionDTO;
import br.com.queiroga.stock.repository.BeverageStockRepository;

@ExtendWith(MockitoExtension.class)
class BeverageStockServiceTest {

	private static final String NON_ALCOHOLIC = "Non-alcoholic";

	private static final String ALCOHOLIC = "alcoholic";

	@Mock
	private BeverageStockRepository beverageStockRepository;
	
	@Mock
	private SectionService sectionService;
	
	@InjectMocks
	private BeverageStockService beverageStockService;
	
	private Section section1;
	private SectionDTO sectionDTO1;

	
	@BeforeEach
	void init() throws Exception {
		section1 = Section.builder().name("Section 1")
				.storedType(createBeverageType(NON_ALCOHOLIC)).build();
	
		when(sectionService.findByName("Section 1")).thenReturn(section1);
			
	}

	@Test
	void testCreated() {
		
		when(beverageStockRepository.save(Mockito.any(BeverageStock.class)))
		.thenReturn(new BeverageStock());
		
		BeverageStock beverageStock = createBeverageStock(NON_ALCOHOLIC,section1);
		
		BeverageStock expected = beverageStockService.create(beverageStock);
		assertNotNull(expected);
		
	}

	@Test
	void testError() {
		
		BeverageStock beverageStock = createBeverageStock(ALCOHOLIC,section1);
		
		Exception ex = assertThrows(InvalidSectionException.class, ()->beverageStockService.create(beverageStock));
		assertNotNull(ex);
	}
	
	private BeverageStock createBeverageStock(String beverageTypeName, Section section) {
		return BeverageStock.builder()
				.type(createBeverageType(beverageTypeName))
				.section(section1).build();
	}

	private BeverageType createBeverageType(String beverageTypeName) {
		return BeverageType.builder().name(beverageTypeName).build();
	}

}
