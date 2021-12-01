package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Produit implements Serializable  {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Long idProduit; // Clé primaire
	private String code;
	private String libelle;
	private float prixUnitaire;
	
	




@ManyToOne
Rayon rayon;

@ManyToOne
Stock stock;

@ManyToMany(cascade = CascadeType.ALL)
private Set <Fournisseur> fournisseur;

@OneToOne
private DetailProduit detailProduit;

@OneToMany(cascade = CascadeType.ALL, mappedBy="produit")
@JsonIgnore
private Set <DetailFacture> detailFacture;


}
