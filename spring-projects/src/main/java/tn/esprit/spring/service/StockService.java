package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.entity.StockPage;
import tn.esprit.spring.entity.StockSearchCriteria;
import tn.esprit.spring.repository.StockCriteriaRepository;
import tn.esprit.spring.repository.StockRepository;

@Service
public class StockService implements IStockService {

	@Autowired
	private StockRepository stockRepository;
	@Autowired
	StockCriteriaRepository stockCriteriaRepository;

	@Override
	public List<Stock> retrieveAllStocks() {
		// TODO Auto-generated method stub
		//List<Stock> stocks = (List<Stock>) stockRepository.findAll();
		List<Stock> stocks =  stockRepository.findAll();
		return stocks;
	
	}

	@Override
	public Stock addStock(Stock s) {
		// TODO Auto-generated method stub
		Date date = new Date();
		s.setCreatedAt(date);
		stockRepository.save(s);
		return s;

	}

	@Override
	public void deleteStock(Long id) {
		// TODO Auto-generated method stub
		stockRepository.deleteById(id);
	}

	@Override
	public Stock updateStock(Stock u) {
		// TODO Auto-generated method stub
		return stockRepository.save(u);
	}

	@Override
	public Stock retrieveStock(Long id) {
		// TODO Auto-generated method stub
		Stock stock= stockRepository.findById(id).orElse(null);
		return stock;

	}

	@Override
	public Page<Stock> findProductsWithPaginationAndSorting(int offset, int pageSize, String field) {
		 Page<Stock> stocks = stockRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
	        return  stocks;
	}

	@Override
	public Page<Stock> getStockFilter(StockPage stockPage, StockSearchCriteria stockSearchCriteria) {
		return stockCriteriaRepository.findAllWithFilters(stockPage, stockSearchCriteria);
	}

	@Override
	public List<?> retrieveStockCreatedPerDay() {

		return stockRepository.retrieveStockCreatedPerDay();
	}
	
	   

}
