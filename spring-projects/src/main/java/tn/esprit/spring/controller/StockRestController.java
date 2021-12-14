package tn.esprit.spring.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.entity.StockPage;
import tn.esprit.spring.entity.StockSearchCriteria;
import tn.esprit.spring.pdfgenerator.StockPDFExporter;
import tn.esprit.spring.service.IStockService;
@RestController 
@CrossOrigin(origins = "*")
@RequestMapping("/stock")
public class StockRestController {
	@Autowired
	IStockService stockService;


	// http://localhost:8080/SpringMVC/stock/retrieve-all-stocks
	@GetMapping("/retrieve-all-stocks")
	@ResponseBody
	public List<Stock> getStocks() {
		List<Stock> listStocks = stockService.retrieveAllStocks();
		return listStocks;
	}

	// http://localhost:8080/SpringMVC/stock/retrieve-stock/8
	@GetMapping("/retrieve-stock/{stock-id}")
	@ResponseBody
	public Stock retrieveStock(@PathVariable("stock-id") Long idStock) {
		return stockService.retrieveStock(idStock);
	}

	// http://localhost:8080/SpringMVC/stock/add-stock

	@PostMapping("/add-stock")
	@ResponseBody
	public Stock addStock(@RequestBody Stock s)
	{
		Stock stock = stockService.addStock(s);
		return stock;
	}

	// http://localhost:8080/SpringMVC/stock/remove-stock/{stock-id}

	@DeleteMapping("/remove-stock/{stock-id}")
	@ResponseBody
	public void removeStock(@PathVariable("stock-id") Long idStock) {
		stockService.deleteStock(idStock);
	}

	// http://localhost:8080/SpringMVC/stock/modify-stock

	@PutMapping("/modify-stock")
	@ResponseBody
	public Stock modifyStock(@RequestBody Stock stock) {
		return stockService.updateStock(stock);	}

	 //http://localhost:8080/SpringMVC/stock/paginationAndSort/0/10/qteMin
	@GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
	private Page<Stock> getStocksWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
		Page<Stock> stocksWithPagination = stockService.findProductsWithPaginationAndSorting(offset, pageSize, field);
		return stocksWithPagination;
	}

	//http://localhost:8080/SpringMVC/stock?libelleStock=lop&pageSize=8&sortDirection=ASC
	@GetMapping
	public ResponseEntity<Page<Stock>> getStockFilter(StockPage stockPage,
			StockSearchCriteria stockSearchCriteria){
		return new ResponseEntity<>(stockService.getStockFilter(stockPage, stockSearchCriteria),
				HttpStatus.OK);
	}
	
	// http://localhost:8080/SpringMVC/stock/export/pdf
	 @GetMapping("/export/pdf")
	    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
	        response.setContentType("application/pdf");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=stock_" + currentDateTime + ".pdf";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Stock> listStocks = stockService.retrieveAllStocks();
	         
	        StockPDFExporter exporter = new StockPDFExporter(listStocks);
	        exporter.export(response);
	         
	    }


	//statistique nb des stock creer par jour 
	//http://localhost:8080/SpringMVC/stock/retrieve-all-stock-jour
	@GetMapping(value ="/retrieve-all-stock-jour")
	public List<?> retrieveStockCreatedPerDay(){

		return stockService.retrieveStockCreatedPerDay();

	}



}
