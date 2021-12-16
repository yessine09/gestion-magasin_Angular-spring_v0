package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.DetailProduit;
@Repository
public interface DetailProduitRepository extends JpaRepository<DetailProduit, Long> {

}
