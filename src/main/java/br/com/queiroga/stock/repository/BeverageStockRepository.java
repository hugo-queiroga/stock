package br.com.queiroga.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.queiroga.stock.model.BeverageStock;

@Repository
public interface BeverageStockRepository extends JpaRepository<BeverageStock, Long>{

}
