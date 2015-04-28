package ntu.music.dao;

import java.util.List;

import ntu.music.pojo.TUserlist;

/**
 * 与用户播放列表相关的连接
 * @author Jeffrey Zou
 *
 */
public interface UserListDao {
	/**
	 * 获取指定用户的播放列表
	 */
	public List<TUserlist> getUserList(Integer userid);
	/**
	 * 根据id删除播放列表
	 */
	public void deleteUserList(String ids);
	/**
	 * 添加播放列表
	 */
	public void addUserList(TUserlist userList);
	/**
	 * 修改播放列表
	 */
	public void update(String name,String newname,Integer userid);
	/**
	 * 根据播放列表名获取播放列表
	 */
	public TUserlist getUserListByName(String name,Integer userid);
}
