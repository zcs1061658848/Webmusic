package ntu.music.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import ntu.music.dao.UserDao;
import ntu.music.pojo.TUser;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
/**
 * 接口实现类 实现与用户表相关
 * @author Jeffrey Zou
 *
 */
@Component("userDao")
public class UserDaoImpl implements UserDao {
	HibernateTemplate ht;
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private void setHibernateTemplate() {
		if (ht == null)
			ht = new HibernateTemplate(sessionFactory);
	}

	@Override
	public TUser getUser(TUser user) {
		setHibernateTemplate();
		List userList= ht.find("from TUser where username=? and password=?", user.getUsername(),user.getPassword());
		TUser userGet = userList.isEmpty() ? null : (TUser) userList.get(0);
		return userGet;
	}

	/**
	 * 添加新的用户
	 */
	@Override
	public TUser addUser(TUser user) {
		setHibernateTemplate();
		ht.save(user);
		return (TUser) ht.find("from TUser where username = ?", user.getUsername()).get(0); 
	}

	/**
	 * 根据用户名获取用户
	 */
	@Override
	public TUser getUserByUsername(String username) {
		setHibernateTemplate();
		List userList= ht.find("from TUser where username=?", username);
		TUser userGet = userList.isEmpty() ? null : (TUser) userList.get(0);
		return userGet;
	}

}
