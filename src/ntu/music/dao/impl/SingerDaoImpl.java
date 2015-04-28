package ntu.music.dao.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import ntu.music.common.Common;
import ntu.music.dao.SingerDao;
import ntu.music.pojo.TSinger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("singerDao")
public class SingerDaoImpl implements SingerDao{
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
	public List<TSinger> getSingerBySexAndArea(final String sex,final String areaid,final Integer page) {
		setHibernateTemplate();
		if(sex==null){
			return ht.execute(new HibernateCallback<List<TSinger>>(){
				@Override
				public List<TSinger> doInHibernate(Session session)
						throws HibernateException {
					Query q=session.createQuery("from TSinger where TArea.areaid = ?");
					q.setString(0,areaid);
					q.setFirstResult((page-1)*Common.pagesize);
					q.setMaxResults((page)*Common.pagesize-1);
					return q.list();
				}
			});
		}
		return  ht.execute(new HibernateCallback<List<TSinger>>(){
			@Override
			public List<TSinger> doInHibernate(Session session)
					throws HibernateException {
				Query q=session.createQuery("from TSinger where sex = ? and TArea.areaid = ?");
				q.setString(0,sex);
				q.setInteger(1, Integer.valueOf(areaid));
				q.setFirstResult((page-1)*Common.pagesize);
				q.setMaxResults((page)*Common.pagesize-1);
				return q.list();
			}
		});
	}

	@Override
	public List<TSinger> getSingerByName(String singerName) {
		setHibernateTemplate();
		return (List<TSinger>)ht.find("from TSinger where singername like %?%", singerName);
	}

	@Override
	public List<TSinger> getSingerByInitial(Character initial,Integer page) {
		setHibernateTemplate();
		return (List<TSinger>)ht.find("from TSinger where GET_FIRST_PINYIN_CHAR(singername) = ? ", initial);
	}

}
