package tn.esprit.spring.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.entity.StockPage;
import tn.esprit.spring.entity.StockSearchCriteria;


@Repository
public class StockCriteriaRepository {
	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;

	public StockCriteriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}


	public Page<Stock> findAllWithFilters(StockPage stockPage,
			StockSearchCriteria stockSearchCriteria){
		CriteriaQuery<Stock> criteriaQuery = criteriaBuilder.createQuery(Stock.class);
		Root<Stock> stockRoot = criteriaQuery.from(Stock.class);
		Predicate predicate = getPredicate(stockSearchCriteria, stockRoot);
		criteriaQuery.where(predicate);
		setOrder(stockPage, criteriaQuery, stockRoot);

		TypedQuery<Stock> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(stockPage.getPageNumber() * stockPage.getPageSize());
		typedQuery.setMaxResults(stockPage.getPageSize());

		Pageable pageable = getPageable(stockPage);

		long stockCount = getStockCount(predicate);

		return new PageImpl<>(typedQuery.getResultList(), pageable, stockCount);
	}

	private Predicate getPredicate(StockSearchCriteria stockSearchCriteria,
			Root<Stock> stockRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(stockSearchCriteria.getLibelleStock())){
			predicates.add(
					criteriaBuilder.like(stockRoot.get("libelleStock"),
							"%" + stockSearchCriteria.getLibelleStock() + "%")
					);
		}
		/*if(Objects.nonNull(stockSearchCriteria.getQteStock())){
			predicates.add(
					criteriaBuilder.like(stockRoot.get("qteStock"),
							"%" + stockSearchCriteria.getQteStock() + "%")
					);
		}*/
	/*	if(Objects.nonNull(stockSearchCriteria.getQteMin())){
			predicates.add(
					criteriaBuilder.like(stockRoot.get("Quantité minimum stock"),
							"%" + stockSearchCriteria.getQteMin()+ "%")
					);
		}*/
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
	private void setOrder(StockPage stockPage,
			CriteriaQuery<Stock> criteriaQuery,
			Root<Stock> stockRoot) {
		if(stockPage.getSortDirection().equals(Sort.Direction.ASC)){
			criteriaQuery.orderBy(criteriaBuilder.asc(stockRoot.get(stockPage.getSortBy())));
		} else {
			criteriaQuery.orderBy(criteriaBuilder.desc(stockRoot.get(stockPage.getSortBy())));
		}
	}

	   private Pageable getPageable(StockPage stockPage) {
	        Sort sort = Sort.by(stockPage.getSortDirection(), stockPage.getSortBy());
	        return PageRequest.of(stockPage.getPageNumber(),stockPage.getPageSize(), sort);
	    }
	   

	    private long getStockCount(Predicate predicate) {
	        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
	        Root<Stock> countRoot = countQuery.from(Stock.class);
	        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
	        return entityManager.createQuery(countQuery).getSingleResult();
	    }

}
