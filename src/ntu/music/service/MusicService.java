package ntu.music.service;

import java.util.List;

import ntu.music.pojo.TArea;
import ntu.music.pojo.TMusic;
import ntu.music.pojo.TSinger;
import ntu.music.pojo.TUser;
/**
 * 业务处理层接口
 * @author Jeffrey Zou
 *
 */
public interface MusicService {
	/**
	 *处理登录 根据用户名密码判断时候拥有此用户
	 */
	public TUser isExistService(TUser login);
	
	/**
	 * 获取树形结构
	 */
	public String getUserListTree(TUser user);
	/**
	 * 获取用户的播放雷彪
	 */
	public String getUserList(TUser user);
	
	/**
	 * 删除播放列表
	 */
	public String deleteUserList(String ids);
	/**
	 * 添加播放列表
	 */
	public String addUserList(String name,TUser user);
	/**
	 * 修改播放列表
	 */
	public String upfateUserList(String name,String newname,TUser user);

	/**
	 * 添加新的用户
	 */
	public TUser addUser(TUser user);
	
	/**
	 * 判断用户名是否可用
	 */
	public String checkUsername(String username);
	
	/**
	 *获取歌手 
	 */
	public List<TSinger> getSingerListByIntial(Character intial,Integer page);
	
	/**
	 * 根据地区和性别获取
	 */
	
	public List<TSinger> getSingerByAreaAndSex(String sex ,String areaid,Integer page);
	
	/**
	 * 很据姓名获取歌手
	 */
	public List<TSinger> getSingerByName(String singerName);
	
	/**
	 * 根据歌手获取歌曲
	 */
	public List<TMusic> getMusicBySinger(Integer singerid);
	
	/**
	 * 根据歌曲名获取歌曲
	 */
	public List<TMusic> getMusicByName(String musicName);
	
	/**
	 * 获取地区
	 */
	public List<TArea> getArea();
	
	/**
	 * 根据专辑获取歌曲
	 */
	public List<TMusic> getMusicByAlbum(Integer albumid);
	

}
