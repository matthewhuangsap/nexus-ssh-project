package it.nexus.core.dao;

import it.nexus.core.models.Base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SuppressWarnings("unchecked")
public class BaseDAO<T extends Base, PK extends Serializable> implements
		IBaseDAO<T>
{
	protected Logger			logger	= LoggerFactory.getLogger(getClass());
	@Resource(name = "sessionFactory")
	protected SessionFactory	sessionFactory;

	protected Class<T>			entityClass;

	public BaseDAO() {
		entityClass = getGenericClass();
        System.out.println(entityClass.getName() + "DAO Constructor Initiling!!!!");
	}

	public BaseDAO(final SessionFactory sessionFactory,
			final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass() {
		Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}

	/**
	 * 保存新增或修改的对象.
	 */
    
	public void save(final T entity) {
        System.out.println("base dao do save method!!!");
		Assert.notNull(entity, "entity不能为空");
//        getSession().beginTransaction();
		getSession().saveOrUpdate(entity);
		logger.debug("save entity: {}", entity);
//        getSession().beginTransaction().commit();
	}

	/**
	 * 删除对象.
	 * 
	 * @param entity
	 *            对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(),
				id);
	}

	/**
	 * 获取全部对象.
	 */
	public List<T> getAll() {
		return find();
	}

	/**
	 * 按id获取对象.
	 */

	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) getSession().get(getGenericClass(), id);
	}

	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	/**
	 * 按id列表获取对象.
	 */
	public List<T> findByIds(List<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> List<X> find(final String hql, final Map<String, Object> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values 
	 * 				数量可变的参数,按顺序绑定.
	 */
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> X findUnique(final String hql, final Map<String, Object> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @return 更新记录数.
	 */
	public int batchExecute(final String hql, final Map<String, Object> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param values
	 *            数量可变的参数,按顺序绑定.
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public Query createQuery(final String queryString,
			final Map<String, Object> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param criterions
	 *            数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
	 * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,可实现新的函数,执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	/**
	 * @see ##initEntity(Object)
	 */
	public void initEntity(List<T> entityList) {
		for (T entity : entityList) {
			Hibernate.initialize(entity);
		}
	}

	/**
	 * 为Query添加distinct transformer.
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * 为Criteria添加distinct transformer.
	 */
	public Criteria distinct(Criteria criteria) {
		criteria
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * 通过Set将不唯一的对象列表唯一化. 主要用于HQL/Criteria预加载关联集合形成重复记录,又不方便使用distinct查询语句时.
	 */
	public <X> List<X> distinct(List list) {
		Set<X> set = new LinkedHashSet<X>(list);
		return new ArrayList<X>(set);
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
