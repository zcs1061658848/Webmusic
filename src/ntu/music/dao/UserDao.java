package ntu.music.dao;

import ntu.music.pojo.TUser;
/**
 * 与用户表相关的数据连接
 *
 */
public interface UserDao {
	/**
	 * 获取用户
	 */
	public TUser getUser(TUser user);
	
	/**
	 * 添加新的用户
	 */
	public TUser addUser(TUser user);
	/**
	 * 根据用户名获取用户
	 */
	public TUser getUserByUsername(String username);

}
