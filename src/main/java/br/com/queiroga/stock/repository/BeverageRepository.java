package br.com.queiroga.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.queiroga.stock.model.Beverage;
import br.com.queiroga.stock.model.enums.BeverageTypeEnum;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Long>{

	@Query(value = "select sum(volume) from Beverage b where b.type = :type")
	Integer findTotalVolumeByType( @Param("type") BeverageTypeEnum type);

}
