package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
// @Data(telmhom kol getter,setter,tostring.....)
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
// @Builder (constructor mt3i kifech nhebou les attribus eli f westou) w b3ed f controller client c =builder.chnia hachtek)
public class Stock  implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idStock; // Clé primaire
	@NonNull
	private int qteStock;
	@NonNull
	private int qteMin;
	private String libelleStock;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="stock")
	@JsonIgnore
	private Set <Produit> produit;

}
