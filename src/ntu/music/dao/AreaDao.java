package ntu.music.dao;

import java.util.List;

import ntu.music.pojo.TArea;

public interface AreaDao {
	/**
	 * 获取所有的地区
	 */
	public List<TArea> getAreas();

}
