package ntu.music.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import ntu.music.dao.AreaDao;
import ntu.music.pojo.TArea;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("areaDao")
public class AreaDaoImpl implements AreaDao {
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
	public List<TArea> getAreas() {
		setHibernateTemplate();
		return (List<TArea>)ht.find("from TArea");
	}

}
