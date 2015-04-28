package ntu.music.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import ntu.music.dao.AreaDao;
import ntu.music.dao.MusicDao;
import ntu.music.dao.SingerDao;
import ntu.music.dao.UserDao;
import ntu.music.dao.UserListDao;
import ntu.music.pojo.TArea;
import ntu.music.pojo.TMusic;
import ntu.music.pojo.TSinger;
import ntu.music.pojo.TUser;
import ntu.music.pojo.TUserlist;
import ntu.music.service.MusicService;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
/**
 * 业务处理层
 * @author Jeffrey Zou
 *
 */
@Service("musicService")
public class MusicServiceImpl implements MusicService {
	@Resource(name="userDao")
	private UserDao userDao;
	@Resource(name="musicDao")
	private MusicDao musicDao;
	@Resource(name="singerDao")
	private SingerDao singerDao;
	@Resource(name="userListDao")
	private UserListDao userListDao;
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	@Resource(name="areaDao")
	private AreaDao areaDao;
	
	/**
	 * 根据用户名密码获取用户
	 */
	@Override
	public TUser isExistService(TUser user) {
		return userDao.getUser(user);
	}

	/**
	 * 获取树形结构
	 */
	
	@Override
	public String getUserListTree(TUser user) {
		JSONArray jsonArray = new JSONArray(); 
		if(user.getRole() != 1){  //改用户为普通用户
			List<TUserlist> list = userListDao.getUserList(user.getUserid());
			JSONObject json2 = new JSONObject();
			json2.put("text", "音乐库");
			jsonArray.add(json2);
			JSONArray jsonArray2 = new JSONArray(); 
			for(TUserlist userlist : list){
				JSONObject json = new JSONObject();
				json.put("text", userlist.getListname());
				json.put("checkbox", true);
				jsonArray2.add(json);
			}
			JSONObject json = new JSONObject();
			json.put("text", "我的音乐");
			json.put("children", jsonArray2);
			jsonArray.add(json);
			
		} else { //为管理员
			JSONObject json = new JSONObject();
			json.put("text", "乐库管理");
			jsonArray.add(json);
		}
		return jsonArray.toString();
	
	}

	/**
	 * 获取播放列表
	 */
	@Override
	public String getUserList(TUser user) {
		List<TUserlist> list = userListDao.getUserList(user.getUserid());
		StringBuffer buffer = new StringBuffer();
		if(list.isEmpty() == true)
			return "";
		buffer.append("<table style='width:100%' id='listtable'>");
		buffer.append("<tr><td></td><td style='white-space:nowrap;'>列表名</td><td style='white-space:nowrap;'>操作</td></tr>");
		for(TUserlist userlist : list){
			buffer.append("<tr id='tr").append(userlist.getListname()).append("'>");
			buffer.append("<td>").append("<input type='checkbox' name='userlistcheckbox' value='").append(userlist.getListid()).append("' >");
			buffer.append("<td class='list' id='").append(userlist.getListname()).append("'>").append(userlist.getListname()).append("</td>");
			buffer.append("<td id='td").append(userlist.getListname()).append("'><input type='button' value='编辑' class='l-btn editbutton'>&nbsp;<input class='l-btn surebutton' id='sure"+userlist.getListname()+"' type='button'  value='确认'> &nbsp;<input class='l-btn cancelbutton' id='cancel"+userlist.getListname()).append("'type='button' value='取消'></td>").append("</td>");
			buffer.append("</tr>");
		}
		buffer.append("</table>");
		return buffer.toString();
	}

	/**
	 * 删除播放列表
	 */
	@Override
	public String deleteUserList(String ids) {
		userListDao.deleteUserList(ids);
		return "success";
	}

	/**
	 * 添加播放列表
	 */
	@Override
	public String addUserList(String name, TUser user) {
		if(userListDao.getUserListByName(name, user.getUserid())==null){
			TUserlist userlist = new TUserlist();
			userlist.setListname(name);
			userlist.setTUser(user);
			userListDao.addUserList(userlist);
			return "success";	
		}else{
			return "error";
		}
		
	}

	/**
	 * 更新播放列表
	 */
	@Override
	public String upfateUserList(String name, String newname, TUser user) {
		if(userListDao.getUserListByName(newname, user.getUserid())==null){
			userListDao.update(name, newname, user.getUserid());
			return "success";
		}else
			
		return "error";
	}

	/**
	 * 添加新的用户
	 */
	@Override
	public TUser addUser(TUser user) {
		return userDao.addUser(user);
		
	}

	/**
	 * 判断用户名是否可用
	 */
	@Override
	public String checkUsername(String username) {
		return userDao.getUserByUsername(username) == null? "success":"error";
	}

	@Override
	public List<TSinger> getSingerListByIntial(Character intial,Integer page) {
		return singerDao.getSingerByInitial(intial,page);
	}

	@Override
	public List<TSinger> getSingerByAreaAndSex(String sex, String areaid,Integer page) {
		return singerDao.getSingerBySexAndArea(sex, areaid,page);
	}

	@Override
	public List<TSinger> getSingerByName(String singerName) {
		return singerDao.getSingerByName(singerName);
	}

	@Override
	public List<TMusic> getMusicBySinger(Integer singerid) {
		return musicDao.getMusicBySinger(singerid);
	}

	@Override
	public List<TMusic> getMusicByName(String musicName) {
		return musicDao.getMusicByMusicName(musicName);
	}

	@Override
	public List<TArea> getArea() {
		return areaDao.getAreas();
	}

	@Override
	public List<TMusic> getMusicByAlbum(Integer albumid) {
		return musicDao.getMusicByAlbum(albumid);
	}

}
