package br.com.queiroga.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queiroga.stock.model.BeverageType;

@Repository
public interface BeverageTypeRepository extends JpaRepository<BeverageType, Long>{

}
