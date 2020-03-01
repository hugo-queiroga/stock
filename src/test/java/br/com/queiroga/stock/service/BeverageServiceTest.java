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

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;

import br.com.queiroga.stock.exception.BusinessException;
import br.com.queiroga.stock.model.Beverage;
import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.model.enums.BeverageTypeEnum;
import br.com.queiroga.stock.repository.BeverageRepository;

@ExtendWith(MockitoExtension.class)
class BeverageServiceTest {


	@Mock
	private BeverageRepository beverageRepository;
	
	@Mock
	private SectionService sectionService;
	
	@InjectMocks
	private BeverageService beverageService;
	
	private Section section1;
	
	@BeforeEach
	void init() throws Exception {
		section1 = Section.builder().id(1L).name("Section 1")
				.storedType(BeverageTypeEnum.NON_ALCOHOLIC)
				.spaceUsed(100).build();
			
	}

	@Test
	void testCreated() {
		when(sectionService.findById(section1.getId())).thenReturn(section1);
		when(beverageRepository.save(Mockito.any(Beverage.class)))
		.thenReturn(new Beverage());
		
		Beverage beverageStock = createBeverage(BeverageTypeEnum.NON_ALCOHOLIC,section1);
		
		Beverage expected = beverageService.create(beverageStock);
		assertNotNull(expected);
		
	}

	@Test
	void testStorageNotAllowed() {
		when(sectionService.findById(section1.getId())).thenReturn(section1);
		Beverage beverageStock = createBeverage(BeverageTypeEnum.ALCOHOLIC,section1);
		
		Exception ex = assertThrows(BusinessException.class, ()->beverageService.create(beverageStock));
		assertNotNull(ex);
	}
	

	@Test
	void testSectionNotFound() {
		when(sectionService.findById(not(eq(1L)))).thenReturn(null);
		Beverage beverageStock = createBeverage(BeverageTypeEnum.NON_ALCOHOLIC,Section.builder().id(2l).build());
		
		Exception ex = assertThrows(BusinessException.class, ()->beverageService.create(beverageStock));
		assertNotNull(ex);
	}
	
	@Test
	void testInsuficientSpace() {
		when(sectionService.findById(section1.getId())).thenReturn(section1);
		Beverage beverage = createBeverage(BeverageTypeEnum.NON_ALCOHOLIC,section1);
		beverage.setVolume(401);
		
		Exception ex = assertThrows(BusinessException.class, ()->beverageService.create(beverage));
		assertNotNull(ex);
	}
	
	
	private Beverage createBeverage(BeverageTypeEnum beverageType, Section section) {
		return Beverage.builder()
				.type(beverageType)
				.section(section)
				.volume(100).build();
	}


}
