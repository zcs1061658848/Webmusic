package ntu.music.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import ntu.music.dao.UserListDao;
import ntu.music.pojo.TUserlist;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;
/**
 * 接口实现类 实现与用户列表相关
 * @author Jeffrey Zou
 *
 */
@Component("userListDao")
public class UserListDaoImpl implements UserListDao {
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

	/**
	 * 根据用户id获取用户的播放列表
	 */
	@Override
	public List<TUserlist> getUserList(Integer userid) {
		setHibernateTemplate();
		return (List<TUserlist>)ht.find("from TUserlist where TUser.userid = ?", userid);
	}

	/**
	 * 删除播放列表
	 */
	@Override
	public void deleteUserList(final String ids) {
		setHibernateTemplate();
		ht.execute(new HibernateCallback<Object>(){
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query=session.createQuery("delete from TUserlist where listid in ("+ids+")");
				query.executeUpdate();
				return null;
			}
		});
	}

	/**
	 * 添加播放列表
	 */
	@Override
	public void addUserList(TUserlist userList) {
		setHibernateTemplate();
		ht.save(userList);
	}

	/**
	 * 修改播放列表
	 */
	@Override
	public void update(final String name, final String newname, final Integer userid) {
		setHibernateTemplate();		
		ht.execute(new HibernateCallback<Object>(){

			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query=session.createQuery("update TUserlist set listname = ? where TUser.userid = ? and listname = ?");
				query.setString(0, newname);
				query.setInteger(1,userid);
				query.setString(2, name);
				query.executeUpdate();
				return null;
			}
			
		});
	}

	/**
	 * 根据名字获取播放列表
	 */
	@Override
	public TUserlist getUserListByName(String name, Integer userid) {
		setHibernateTemplate();
		List<TUserlist> list=(List<TUserlist>)ht.find("from TUserlist where TUser.userid = ? and listname= ?" ,userid ,name);
		return list.isEmpty()?null:list.get(0);
	}

}
