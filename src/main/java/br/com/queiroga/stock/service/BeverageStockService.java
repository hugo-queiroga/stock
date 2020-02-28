package br.com.queiroga.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queiroga.stock.exception.InvalidSectionException;
import br.com.queiroga.stock.model.BeverageStock;
import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.repository.BeverageStockRepository;

@Service
public class BeverageStockService {

	private final BeverageStockRepository repository;
	
	private final SectionService sectionService;
	
	@Autowired
	public BeverageStockService(BeverageStockRepository beverageStockRepository, SectionService sectionService) {
		this.repository = beverageStockRepository;
		this.sectionService = sectionService;
	}
	
	public BeverageStock create(BeverageStock beverageStock) {
		
		Section section = sectionService.findByName(beverageStock.getSection().getName());
		
		if(section.getStoredType().equals(beverageStock.getType())) {
			return repository.save(beverageStock);	
		} else {
			throw new InvalidSectionException("Storage not allowed in section");
		}
		
		
	}
	
}
