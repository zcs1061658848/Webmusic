package ntu.music.pojo;

/**
 * TScore entity. @author MyEclipse Persistence Tools
 */

public class TScore implements java.io.Serializable {

	// Fields

	private Integer id;
	private TUser TUser;
	private TMusic TMusic;
	private Integer score;

	// Constructors

	/** default constructor */
	public TScore() {
	}

	/** full constructor */
	public TScore(TUser TUser, TMusic TMusic, Integer score) {
		this.TUser = TUser;
		this.TMusic = TMusic;
		this.score = score;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public TMusic getTMusic() {
		return this.TMusic;
	}

	public void setTMusic(TMusic TMusic) {
		this.TMusic = TMusic;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}