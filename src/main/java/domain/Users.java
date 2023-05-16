package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.persistence.annotations.PrimaryKey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "Users.findByUsernameAndPassword", 
	query = "SELECT u FROM Users u WHERE username = :username AND password = :password") })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "username")
@Getter
@Setter
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKeyJoinColumn
	@Id
	private String username;

	private String password;
	
	private List<Integer> favorieten;
	
	private Integer maxAantalFavs;
	
	private Boolean enabled;

	public Users(String username, String password, ArrayList<Integer> favorieten, Integer maxAantalFavs, Boolean enabled) {
		this.username = username;
		this.password = password;
		this.favorieten = favorieten;
		this.maxAantalFavs = maxAantalFavs;
		this.enabled = enabled;
	}
}
