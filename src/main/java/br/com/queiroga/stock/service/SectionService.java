package br.com.queiroga.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.queiroga.stock.exception.NotFoundException;
import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.repository.SectionRepository;

@Service
public class SectionService {

	private SectionRepository sectionRepository;
	
	@Autowired
	public SectionService(SectionRepository sectionRepository) {
		
		this.sectionRepository = sectionRepository;
		
	}
	
	public Section findByName(String name) {
		return sectionRepository.findByName(name).orElseThrow(() -> new NotFoundException("Section not found"));
	}
	
}
