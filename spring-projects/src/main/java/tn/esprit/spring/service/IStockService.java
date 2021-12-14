package tn.esprit.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;


import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.entity.StockPage;
import tn.esprit.spring.entity.StockSearchCriteria;

public interface IStockService {
	List<Stock> retrieveAllStocks();

	Stock addStock(Stock s);

	void deleteStock(Long id);

	Stock updateStock(Stock u);

	Stock retrieveStock(Long id);
	Page<Stock> findProductsWithPaginationAndSorting(int offset,int pageSize,String field);
	public Page<Stock> getStockFilter(StockPage stockPage,
			StockSearchCriteria stockSearchCriteria);
	
	
	public List<?> retrieveStockCreatedPerDay();

}
