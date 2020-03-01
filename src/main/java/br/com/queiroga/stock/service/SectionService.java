package br.com.queiroga.stock.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.model.enums.BeverageTypeEnum;
import br.com.queiroga.stock.repository.SectionRepository;

@Service
public class SectionService {

	private SectionRepository sectionRepository;
	
	@Autowired
	public SectionService(SectionRepository sectionRepository) {
		
		this.sectionRepository = sectionRepository;
		
	}
	
	public Section findById(Long id){
		
		return sectionRepository.findById(id).orElse(null);
	}

	public Section save(Section section) {
		return sectionRepository.save(section);
	
	}

	public List<Section> findStorageLocationsByType(BeverageTypeEnum type) {
		
		List<Section> sections = sectionRepository.findByStoredTypeOrStoredTypeIsNull(type);
		return sections.stream().filter(s -> s.getSpaceUsed() < type.getMaxVolume()).collect(Collectors.toList());
		
	}
	
}
