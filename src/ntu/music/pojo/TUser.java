package ntu.music.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String username;
	private String password;
	private String email;
	private Integer role;
	private Set TUserlists = new HashSet(0);
	private Set TScores = new HashSet(0);

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** full constructor */
	public TUser(String username, String password, String email, Integer role,
			Set TUserlists, Set TScores) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.TUserlists = TUserlists;
		this.TScores = TScores;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Set getTUserlists() {
		return this.TUserlists;
	}

	public void setTUserlists(Set TUserlists) {
		this.TUserlists = TUserlists;
	}

	public Set getTScores() {
		return this.TScores;
	}

	public void setTScores(Set TScores) {
		this.TScores = TScores;
	}

}