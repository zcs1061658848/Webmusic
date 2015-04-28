package ntu.music.pojo;

import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for TSinger entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see ntu.music.pojo.TSinger
  * @author MyEclipse Persistence Tools 
 */
public class TSingerDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TSingerDAO.class);
		//property constants
	public static final String SINGERNAME = "singername";
	public static final String SEX = "sex";
	public static final String DESCRIPTION = "description";
	public static final String PICTURE = "picture";



    
    public void save(TSinger transientInstance) {
        log.debug("saving TSinger instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TSinger persistentInstance) {
        log.debug("deleting TSinger instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TSinger findById( java.lang.Integer id) {
        log.debug("getting TSinger instance with id: " + id);
        try {
            TSinger instance = (TSinger) getSession()
                    .get("ntu.music.pojo.TSinger", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TSinger instance) {
        log.debug("finding TSinger instance by example");
        try {
            List results = getSession()
                    .createCriteria("ntu.music.pojo.TSinger")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding TSinger instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TSinger as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySingername(Object singername
	) {
		return findByProperty(SINGERNAME, singername
		);
	}
	
	public List findBySex(Object sex
	) {
		return findByProperty(SEX, sex
		);
	}
	
	public List findByDescription(Object description
	) {
		return findByProperty(DESCRIPTION, description
		);
	}
	
	public List findByPicture(Object picture
	) {
		return findByProperty(PICTURE, picture
		);
	}
	

	public List findAll() {
		log.debug("finding all TSinger instances");
		try {
			String queryString = "from TSinger";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TSinger merge(TSinger detachedInstance) {
        log.debug("merging TSinger instance");
        try {
            TSinger result = (TSinger) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TSinger instance) {
        log.debug("attaching dirty TSinger instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TSinger instance) {
        log.debug("attaching clean TSinger instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}