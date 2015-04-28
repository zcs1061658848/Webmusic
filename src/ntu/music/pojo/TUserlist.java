package ntu.music.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TUserlist entity. @author MyEclipse Persistence Tools
 */

public class TUserlist implements java.io.Serializable {

	// Fields

	private Integer listid;
	private TUser TUser;
	private String listname;
	private Set TMusiclists = new HashSet(0);

	// Constructors

	/** default constructor */
	public TUserlist() {
	}

	/** full constructor */
	public TUserlist(TUser TUser, String listname, Set TMusiclists) {
		this.TUser = TUser;
		this.listname = listname;
		this.TMusiclists = TMusiclists;
	}

	// Property accessors

	public Integer getListid() {
		return this.listid;
	}

	public void setListid(Integer listid) {
		this.listid = listid;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public String getListname() {
		return this.listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	public Set getTMusiclists() {
		return this.TMusiclists;
	}

	public void setTMusiclists(Set TMusiclists) {
		this.TMusiclists = TMusiclists;
	}

}