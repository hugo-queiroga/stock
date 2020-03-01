package br.com.queiroga.stock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queiroga.stock.model.Section;
import br.com.queiroga.stock.model.enums.BeverageTypeEnum;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long>{

	Optional<Section> findByName(String name);


	List<Section> findByStoredTypeOrStoredTypeIsNull(BeverageTypeEnum type);
	
}
