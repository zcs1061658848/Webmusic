package ntu.music.dao;

import java.util.List;

import ntu.music.pojo.TMusic;

public interface MusicDao {
	/**
	 * 获取歌手的所欲歌曲 
	 */
	public List<TMusic> getMusicBySinger(Integer singerid);

	/**
	 * 根据歌名获取歌曲
	 */
	public List<TMusic> getMusicByMusicName(String musicName); 
	
	/**
	 * 根据专辑获取歌曲
	 */
	public List<TMusic> getMusicByAlbum(Integer albumid);
	
}
