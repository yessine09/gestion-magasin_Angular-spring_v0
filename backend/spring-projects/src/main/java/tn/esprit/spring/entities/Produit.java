package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name="Produit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Produit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idProduit")
	private Long idProduit;
	private String code; 
	private String libelle;
	private float prixUnitaire;
	@Enumerated(EnumType.STRING)
	private CategorieProduit categorieProduit;
	@OneToOne
	private DetailProduit detailProduit ;
	@ManyToOne 
	private Stock stock;
	@ManyToOne
	private Rayon rayon;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="produit")
	private Set<DetailFacture> detailFactures;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Fournisseur> fournisseurs;

}
