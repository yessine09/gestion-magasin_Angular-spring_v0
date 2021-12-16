package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name ="Rayon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rayon implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idRayon")
	private Long idRayon;
	private String codeRayon; 
	private String libelleRayon;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="rayon")
	private Set<Produit> produits;
	
	
}
