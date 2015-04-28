package ntu.music.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TMusic entity. @author MyEclipse Persistence Tools
 */

public class TMusic implements java.io.Serializable {

	// Fields
	private Integer musicid;
	private TAlbum TAlbum;
	private TSinger TSinger;
	private String musicname;
	private String musicsrc;
	private String music;
	private Set TMusiclists = new HashSet(0);
	private Set TScores = new HashSet(0);

	// Constructors

	/** default constructor */
	public TMusic() {
	}


	public Integer getMusicid() {
		return musicid;
	}

	public void setMusicid(Integer musicid) {
		this.musicid = musicid;
	}

	public TAlbum getTAlbum() {
		return TAlbum;
	}

	public void setTAlbum(TAlbum tAlbum) {
		TAlbum = tAlbum;
	}

	public TSinger getTSinger() {
		return TSinger;
	}

	public void setTSinger(TSinger tSinger) {
		TSinger = tSinger;
	}


	public String getMusicname() {
		return musicname;
	}


	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}


	public TMusic(Integer musicid, ntu.music.pojo.TAlbum tAlbum,
			ntu.music.pojo.TSinger tSinger, String musicname, String musicsrc,
			String music, Set tMusiclists, Set tScores) {
		super();
		this.musicid = musicid;
		TAlbum = tAlbum;
		TSinger = tSinger;
		this.musicname = musicname;
		this.musicsrc = musicsrc;
		this.music = music;
		TMusiclists = tMusiclists;
		TScores = tScores;
	}


	public String getMusicsrc() {
		return musicsrc;
	}


	public void setMusicsrc(String musicsrc) {
		this.musicsrc = musicsrc;
	}


	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public Set getTMusiclists() {
		return TMusiclists;
	}

	public void setTMusiclists(Set tMusiclists) {
		TMusiclists = tMusiclists;
	}

	public Set getTScores() {
		return TScores;
	}

	public void setTScores(Set tScores) {
		TScores = tScores;
	}
	
}
