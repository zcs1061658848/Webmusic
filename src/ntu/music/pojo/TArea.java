package ntu.music.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * TArea entity. @author MyEclipse Persistence Tools
 */

public class TArea implements java.io.Serializable {

	// Fields

	private Integer areaid;
	private String areaname;
	private Set TSingers = new HashSet(0);

	// Constructors

	/** default constructor */
	public TArea() {
	}

	/** full constructor */
	public TArea(String areaname, Set TSingers) {
		this.areaname = areaname;
		this.TSingers = TSingers;
	}

	// Property accessors

	public Integer getAreaid() {
		return this.areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getAreaname() {
		return this.areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public Set getTSingers() {
		return this.TSingers;
	}

	public void setTSingers(Set TSingers) {
		this.TSingers = TSingers;
	}

}