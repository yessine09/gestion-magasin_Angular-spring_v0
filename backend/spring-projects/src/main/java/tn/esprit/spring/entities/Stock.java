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
@Table (name = "Stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Stock implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idStock")
	private Long idStock;
	private Integer qteStock;
	private Integer qteMin ;
	private String libelleStock;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="stock")
	private Set<Produit> produits;
	
}
