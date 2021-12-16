package tn.esprit.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "Fournisseur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fournisseur implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idFournisseur")
	private Long idFournisseur ; 
	private String codeFournisseur;
	private String libelleFournisseur;
	
}
