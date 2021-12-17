


package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @Data -> {} entity,getter,setter ... 
// @RequiredArgsConstructor  ->definit les constructor avec seulements les attributs NotNull
@ToString
@EqualsAndHashCode

// @FieldDefaults(level = AccessLevel.PUBLIC) wala default wala protect selon chnia theb modifie accée

//@Builder (constructor mt3i kifech nhebou les attribus eli f westou) w b3ed f controller client c =builder.chnia hachtek)



public class Client  implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idClient; // Clé primaire
	@NotNull
	private String nom;
	@NotNull
	private String prenom;
	@Temporal (TemporalType.DATE)
	private Date DateNaissance;
	private String email;
	private String password;
	@Enumerated (EnumType.STRING)
	private CategorieClient categorieClient ;
	@Enumerated (EnumType.STRING)
	//private String profession = Profession.DOCTEUR;
	private Profession profession;



	
	
	public enum CategorieClient {
		FIDELE,
		ORDINAIRE,
		PREMIUM
	}
	
	public enum Profession{
		DOCTEUR,
		INGENIEUR,
		ETUDIANT,
		COMMERCIAL,
		CADRE,
		AUTRE
	}
	
	
	
	
@OneToMany(cascade = CascadeType.ALL, mappedBy="client")
@EqualsAndHashCode.Exclude
@ToString.Exclude
@JsonIgnore
private List <Facture> facture;






	
	
	
	
	
	
	
	
	
	
}
