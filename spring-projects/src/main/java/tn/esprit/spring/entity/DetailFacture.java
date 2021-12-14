package tn.esprit.spring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class DetailFacture  implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	
	private Long idDetailFacture; // Clé primaire
	private int qte;
	private float prixTotal;
	private int pourcentageRemise;
	private float montantRemise;
	
@ManyToOne
Produit produit;

@ManyToOne
Facture facture;

}
