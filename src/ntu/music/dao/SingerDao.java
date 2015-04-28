package ntu.music.dao;

import java.util.List;

import ntu.music.pojo.TSinger;

public interface SingerDao {
	
	/**
	 * 根据性别和地区查找歌手
	 */
	public List<TSinger> getSingerBySexAndArea(String sex,String areaid,Integer page);
	
	/**
	 * 根据歌手名模糊查找歌手
	 */
	public List<TSinger> getSingerByName(String singerName);
	
	/**
	 * 根据首字母获取歌手
	 */
	public List<TSinger> getSingerByInitial(Character initial,Integer page);
	
	
	
}
