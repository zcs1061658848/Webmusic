package ntu.music.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ntu.music.pojo.TSinger;
import ntu.music.pojo.TUser;
import ntu.music.service.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制层 处理登录和业务请求
 * 
 * @author Jeffrey Zou
 * 
 */
@Controller
@RequestMapping("music")
public class MusicAction {
	/**
	 * 自定注入业务处理层实现类
	 */
	@Autowired
	MusicService musicService;

	/**
	 * 处理获取用户指定的播放列表
	 */
	@RequestMapping("list/userlist")
	@ResponseBody
	public String getUserList(@RequestParam(required = false) String title) {

		return "userlist"; 

	}

	/**
	 * 处理获取曲库
	 */
	@RequestMapping("list/library")
	public ModelAndView getMusicLibrary(@RequestParam(required = false) Integer page,@RequestParam(required = false) Character intial,@RequestParam(required = false) String areaid,@RequestParam(required = false) String sex,HttpServletRequest request) {
		List<TSinger> list;
		if(page==null)
		{
			page=1;
		}
		if(areaid==null||"".equals(areaid)){
			if(intial==null){
				intial = 'A';
			}
			list=musicService.getSingerListByIntial(Character.toUpperCase(intial),page);
			request.setAttribute("intial", intial);
		}else{
			 list=musicService.getSingerByAreaAndSex(sex,areaid,page);
			 request.setAttribute("sex", sex);
			 request.setAttribute("area", areaid);
		}
		request.setAttribute("pagecount", list.size()/10+1);
		request.setAttribute("areaList",musicService.getArea());
		request.setAttribute("currentpage", page);
		request.setAttribute("singerList", list);	
		return new ModelAndView("../../../jsp/library");

	}
	
	/**
	 * 获取左边的树形结构
	 */
	@RequestMapping("getTree")
	@ResponseBody
	public String getTree(HttpSession session) {
		String returnvalue="";
		try {
			returnvalue = new String(musicService.getUserListTree((TUser)session.getAttribute("user")).getBytes("utf-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnvalue;
	}
	
	/**
	 * 获取用户的所有播放列表
	 */
	@RequestMapping("getUserList")
	@ResponseBody
	public String getUserList(HttpSession session) {
		String returnvalue="";
		try {
			returnvalue = new String(musicService.getUserList((TUser)session.getAttribute("user")).getBytes("utf-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return returnvalue;
	}

	
	/**
	 * 删除播放列表
	 */
	@RequestMapping("deleteUserList")
	@ResponseBody
	public String deleteUserList(@RequestParam(required=false) String ids){
		return musicService.deleteUserList(ids);
	}
	
	/**
	 * 修改播放列表
	 */
	@RequestMapping("edituserlist")
	@ResponseBody
	public String editUserList(@RequestParam(required=false) String name,@RequestParam(required=false) String newname,HttpSession session){
		return musicService.upfateUserList(name, newname, (TUser)session.getAttribute("user"));
	}
	
	/**
	 * 修改播放列表
	 */
	@RequestMapping("addUserList")
	@ResponseBody
	public String addUserList(@RequestParam(required=false) String name,HttpSession session){
		return musicService.addUserList(name, (TUser)session.getAttribute("user"));
	}
	
	/**
	 * 获取歌手的歌曲
	 */
	@RequestMapping("getmusicbysinger")
	public ModelAndView getMusicBySinger(@RequestParam(required = false) Integer singerid,HttpServletRequest request) {
		request.setAttribute("musiclist",musicService.getMusicBySinger(singerid));
		request.setAttribute("singerid", singerid);
		return new ModelAndView("../../jsp/singermusic");

	}
	/**
	 * 获取专辑的歌曲
	 */
	@RequestMapping("getMusicByAlbum")
	public ModelAndView getMusicByAlbum(@RequestParam(required = false) Integer albumid,HttpServletRequest request,@RequestParam(required = false)Integer singerid) {
		request.setAttribute("musiclist",musicService.getMusicByAlbum(albumid));
		request.setAttribute("singerid", singerid);
		return new ModelAndView("../../jsp/albummusic");

	}
}
