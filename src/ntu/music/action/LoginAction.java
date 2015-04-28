package ntu.music.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import ntu.music.pojo.TUser;
import ntu.music.service.MusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("login")
public class LoginAction {
	/**
	 * 自定注入业务处理层实现类
	 */
	@Autowired
	MusicService musicService;
	/**
	 *  处理登录请求
	 */
	@RequestMapping("login")
	@ResponseBody
	public void login(@ModelAttribute TUser user, PrintWriter printWriter,
			HttpSession session) {
		TUser login1 = musicService.isExistService(user);
		JSONObject jo = new JSONObject();
		if (login1 != null) {
			jo.put("success", true);
			jo.put("msg", "success");
			session.setAttribute("user", login1);
			try {
				printWriter.write(new String(jo.toString().getBytes("utf-8"),
						"ISO8859-1"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			jo.put("msg", "用户名或取密码不正确");
			try {
				printWriter.write(new String(jo.toString().getBytes("utf-8"),"ISO8859-1"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		printWriter.flush();
		printWriter.close();

	}
	/**
	 *  处理登录请求
	 */
	@RequestMapping("layout")
	public ModelAndView layout(HttpSession session){
		session.removeAttribute("user");
		return  new ModelAndView("redirect:/jsp/login.jsp");
		
	}
	
	/**
	 * 处理注册请求
	 */
	@RequestMapping("register")
	@ResponseBody
	public String register(@ModelAttribute TUser user,HttpSession session){
		user.setRole(2);//设定为普通用户
		session.setAttribute("user", musicService.addUser(user));
		return "success";
	}
	/**
	 * 判断用户名是否已经存在
	 */
	@RequestMapping("checkusername")
	@ResponseBody
	public String checkUsername(@RequestParam(required=false) String username){
		return musicService.checkUsername(username);
	}

}
