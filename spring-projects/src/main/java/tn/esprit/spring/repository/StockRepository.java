package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query(value="SELECT COUNT(*) AS nbreStock , DATE_FORMAT(created_at,'%d/%m/%Y') AS created_at from Stock GROUP BY DATE_FORMAT(created_at,'%d/%m/%Y')"
			, nativeQuery=true)
				List<?> retrieveStockCreatedPerDay();
}
