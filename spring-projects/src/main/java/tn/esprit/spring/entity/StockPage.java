package tn.esprit.spring.entity;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class StockPage {
	private int pageNumber =0;
	private int pageSize = 10;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy = "libelleStock";

}
