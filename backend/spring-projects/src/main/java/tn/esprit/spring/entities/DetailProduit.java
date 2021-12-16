package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name="DetailProduit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetailProduit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idDetailProduit")
	private Long idDetailProduit;
	@Temporal(TemporalType.DATE)
	private Date dateCreation ;
	@Temporal(TemporalType.DATE)
	private Date dateDerniereModification;
	private CategorieProduit categorieProduit;
	@OneToOne(mappedBy="detailProduit")
	private Produit produit;
	
	
	
}
