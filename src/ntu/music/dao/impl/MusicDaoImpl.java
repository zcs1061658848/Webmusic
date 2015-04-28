package ntu.music.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import ntu.music.dao.MusicDao;
import ntu.music.pojo.TMusic;

@Component("musicDao")
public class MusicDaoImpl implements MusicDao {
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
	public List<TMusic> getMusicBySinger(Integer singerid) {
		setHibernateTemplate();
		return (List<TMusic>)ht.find("from TMusic where TSinger.singerid = ?", singerid);
	}

	@Override
	public List<TMusic> getMusicByMusicName(String musicName) {
		setHibernateTemplate();
		return (List<TMusic>)ht.find("from TMusic where musicname like %?%", musicName);
	}

	@Override
	public List<TMusic> getMusicByAlbum(Integer albumid) {
		setHibernateTemplate();
		return (List<TMusic>)ht.find("from TMusic where TAlbum.albumid = ?" ,albumid);
	}

}
