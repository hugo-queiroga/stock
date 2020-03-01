package br.com.queiroga.stock.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.queiroga.stock.exception.BusinessException;
import br.com.queiroga.stock.model.Beverage;
import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.model.enums.BeverageTypeEnum;
import br.com.queiroga.stock.model.enums.SortEnum;
import br.com.queiroga.stock.repository.BeverageRepository;

@Service
public class BeverageService {

	private final BeverageRepository repository;
	
	private final SectionService sectionService;
	
	@Autowired
	public BeverageService(BeverageRepository beverageStockRepository, SectionService sectionService) {
		this.repository = beverageStockRepository;
		this.sectionService = sectionService;
	}
	
	@Transactional
	public Beverage create(Beverage beverage) {
		
		Section section = sectionService.findById(beverage.getSection().getId());
		beverage.setSection(section);
		
		validate(beverage);
		
		beverage.setDate(new Date());
		
		repository.save(beverage);	
		
		updateSection(section,beverage.getType(),beverage.getVolume());
		
		return beverage;
				
	}

	public List<Beverage> listAll(SortEnum dateSorting, SortEnum sectionSorting) {
		
		Sort sort = createSort(dateSorting, sectionSorting);
		
		return repository.findAll(sort);
		
	}
	

	public List<Beverage> findByType(BeverageTypeEnum type ,SortEnum dateSorting, SortEnum sectionSorting) {
		
		return repository.findAll(createExample(type), createSort(dateSorting, sectionSorting));
		
	}

	private Example<Beverage> createExample(BeverageTypeEnum type) {
		return Example.of(Beverage.builder().type(type).build());
	}

	private Sort createSort(SortEnum dateSorting, SortEnum sectionSorting) {
		Order dateOrder = new Order(Direction.fromString(dateSorting.name()), "date");
		Order sectionOrder = new Order(Direction.fromString(sectionSorting.name()), "section.name");
		List<Order> orders = Arrays.asList(dateOrder,sectionOrder);
		return Sort.by(orders);

	}
	
	

	private void updateSection(Section section, BeverageTypeEnum type, Integer volume) {
		
		if(section.getStoredType() == null) {
			section.setStoredType(type);
		}
		section.addVolume(volume);
		sectionService.save(section);
		
	}

	private void validate(Beverage beverage){
		Section section = beverage.getSection();
		if(section == null) {
			throw new BusinessException("Section not found");
		}
		
		if(section.getStoredType() != null && !section.getStoredType().equals(beverage.getType())) {
			throw new BusinessException("Storage not allowed in section");
		}
		
		if(section.getSpaceUsed() + beverage.getVolume() > beverage.getType().getMaxVolume()) {
			throw new BusinessException("Insufficient space");
		}
		
	}

	public Integer getTotalVolumeByType(BeverageTypeEnum type) {
		Integer total = repository.findTotalVolumeByType(type);
		return total == null ? 0 : total ;
	
	}

	public Integer getTotalVolumeBySection(Long sectionId) {
		Section section = sectionService.findById(sectionId);
		return section == null ? 0 : section.getSpaceUsed();
	}

}
