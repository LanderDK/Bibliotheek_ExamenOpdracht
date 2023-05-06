package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Account.findByUsernameAndPassword", 
	query = "SELECT a FROM Account a WHERE username = :username AND password = :password") })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "username")
@Getter
@Setter
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountID;

	private String username;

	private String password;

	private String role;
	
	private List<Integer> favorieten;
	
	private Integer maxAantalFavs;

	public Account(String username, String password, String role, ArrayList<Integer> favorieten, Integer maxAantalFavs) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.favorieten = favorieten;
		this.maxAantalFavs = maxAantalFavs;
	}
}
