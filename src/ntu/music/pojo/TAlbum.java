package ntu.music.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TAlbum entity. @author MyEclipse Persistence Tools
 */

public class TAlbum implements java.io.Serializable {

	// Fields

	private Integer albumid;
	private TSinger TSinger;
	private String albumname;
	private Date date;
	private Set TMusics = new HashSet(0);

	// Constructors

	/** default constructor */
	public TAlbum() {
	}

	/** full constructor */
	public TAlbum(TSinger TSinger, String albumname, Date date, Set TMusics) {
		this.TSinger = TSinger;
		this.albumname = albumname;
		this.date = date;
		this.TMusics = TMusics;
	}

	// Property accessors

	public Integer getAlbumid() {
		return this.albumid;
	}

	public void setAlbumid(Integer albumid) {
		this.albumid = albumid;
	}

	public TSinger getTSinger() {
		return this.TSinger;
	}

	public void setTSinger(TSinger TSinger) {
		this.TSinger = TSinger;
	}

	public String getAlbumname() {
		return this.albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set getTMusics() {
		return this.TMusics;
	}

	public void setTMusics(Set TMusics) {
		this.TMusics = TMusics;
	}

}