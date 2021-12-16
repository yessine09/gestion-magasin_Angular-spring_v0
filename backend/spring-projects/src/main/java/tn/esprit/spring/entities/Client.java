package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "Client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idClient")
	private Long idClient ; 
	private String nom;
	private String prenom ; 
	@Temporal(TemporalType.DATE)
	private Date dateNaissance; 
	private String email; 
	private String password ; 
	@Enumerated(EnumType.STRING)
	private CategorieClient categorieClient ; 
	@Enumerated(EnumType.STRING)
	private Profession profession ; 
	@OneToMany(cascade = CascadeType.ALL, mappedBy="client")
	private Set<Facture> factures;
	private boolean fidele;
		    @Column(name="active")
	    private int isActive;
	    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinTable(name="user_role",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles;

	    public Client(Client client) {
	        this.idClient = client.getIdClient();
	        this.email = client.getEmail();
	        this.password = client.getPassword();
	        this.isActive = client.getIsActive();
	        this.roles = client.getRoles();
	    }
}
