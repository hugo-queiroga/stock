package br.com.queiroga.stock.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queiroga.stock.exception.InvalidSectionException;
import br.com.queiroga.stock.model.BeverageStock;
import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.model.dto.BeverageStockDTO;
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
	
	public BeverageStock create(BeverageStock beverage) {
		
		Section section = sectionService.findByName(beverage.getSection().getName());
		
		if(section.getStoredType().equals(beverage.getType())) {
			return repository.save(beverage);	
		} else {
			throw new InvalidSectionException("Storage not allowed in section");
		}
		
		
	}

	private BeverageStock createBeverageEntity(BeverageStockDTO beverage, Section section) {

			BeverageStock beverageStock = new BeverageStock();
			BeanUtils.copyProperties(beverage, beverageStock);
			beverageStock.setSection(section);
			beverageStock.setType(section.getStoredType());
			beverageStock.setDate(new Date());
			return beverageStock;

	}
	
}
