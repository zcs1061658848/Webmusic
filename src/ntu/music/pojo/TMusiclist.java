package ntu.music.pojo;

/**
 * TMusiclist entity. @author MyEclipse Persistence Tools
 */

public class TMusiclist implements java.io.Serializable {

	// Fields

	private Integer id;
	private TUserlist TUserlist;
	private TMusic TMusic;

	// Constructors

	/** default constructor */
	public TMusiclist() {
	}

	/** full constructor */
	public TMusiclist(TUserlist TUserlist, TMusic TMusic) {
		this.TUserlist = TUserlist;
		this.TMusic = TMusic;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TUserlist getTUserlist() {
		return this.TUserlist;
	}

	public void setTUserlist(TUserlist TUserlist) {
		this.TUserlist = TUserlist;
	}

	public TMusic getTMusic() {
		return this.TMusic;
	}

	public void setTMusic(TMusic TMusic) {
		this.TMusic = TMusic;
	}

}