package br.com.mrcsfelipe.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class GenericRepositoryImpl<T, ID extends Serializable> implements GenericRepository<T, ID>	 {

	@PersistenceContext(unitName="markFullOne")
	private EntityManager em;
	
	private Class<T> entityClass;
	
	public enum MatchMode { START, END, EXACT, ANYWHERE }
	public enum Order { ASC, DESC;}
	
	public GenericRepositoryImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public GenericRepositoryImpl(EntityManager em) {
		this.em = em;
	}
	public GenericRepositoryImpl(Class<T> entityClass, EntityManager em) {
		this.em = em;
		this.entityClass = entityClass;
	}
	
	public void save(T entity){
		getEm().persist(entity);
	}
	
	public void delete(ID id, Class<T> classe) throws Exception{
		
		
		T entityToBeRemoved = getEm().getReference(classe, id);
		if (entityToBeRemoved != null) {
			getEm().remove(entityToBeRemoved);
		} else {
			throw new NullPointerException("ID ou Entity nullo.");
		}
	}
	
	public T update(T entity){
		return getEm().merge(entity);
	}
	
	public T findByID(ID id){
		return getEm().find(entityClass, id);
	}
	
	

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEm().createQuery(cq).getResultList();
    }
	/**
	 * begin == Posicao do primeiro elemento da busca
	 * end == Até quando vai percorer a busca
	 * 
	 * 
	 * Exemplo:
	 * 
	 * List<Person> listPersonWithIdOf10To15 = dao.findAll(10, 15);
	 * */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll(int begin, int end) {
        CriteriaQuery cq = getEm().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEm().createQuery(cq).setFirstResult(begin).setMaxResults(end).getResultList();
    }
	
	
	/**
	 * Order == ordenação , se eh ASC ou DESC
	 * propertiesOrder == Propriedade da Classe em que deseja ordernar.
	 * 
	 * 
	 * 
	 * Exemplo:
	 * 
	 * List<Person> listPersonOrdeningByNameAndAge = dao.findAll(Order.ASC, "name", "age"); //vai ordernar pelo nome e depois pela idade
	 * */
	public List<T> findAll(Order order, String... propertiesOrder) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		for (String propertyOrder : propertiesOrder) {
			if (order.equals(Order.ASC)) {
				orders.add(cb.asc(root.get(propertyOrder)));
			} else {
				orders.add(cb.desc(root.get(propertyOrder)));
			}
		}
		cq.orderBy(orders);
		return getEm().createQuery(cq).getResultList();
	}
	
	/**
	 * Order == ordenação , se eh ASC ou DESC
	 * propertiesOrder == Propriedade da Classe em que deseja ordernar.
	 * begin == Posicao do primeiro elemento da busca
	 * end == Até quando vai percorer a busca
	 * 
	 * Exemplo:
	 * 
	 * List<Person> listPersonOrdeningByNameAndAge = dao.findAll(Order.ASC, "name", "age"); //vai ordernar pelo nome e depois pela idade
	 * */
	public List<T> findAll(Order order, int begin, int end ,String... propertiesOrder) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		
		List<javax.persistence.criteria.Order> orders = new ArrayList<>();
		for (String propertyOrder : propertiesOrder) {
			if (order.equals(Order.ASC)) {
				orders.add(cb.asc(root.get(propertyOrder)));
			} else {
				orders.add(cb.desc(root.get(propertyOrder)));
			}
		}
		cq.orderBy(orders);
		return getEm().createQuery(cq).setFirstResult(begin).setMaxResults(end).getResultList();
	}
	
	/**
	 * 
	 * property == Propriedade da Classe
	 * value == valor da property 
	 * 
	 * Exemplo:
	 * 
	 * List<Person> listPersonWithNameMarcos = dao.findByProperty("name", "Marcos");
	 * 
	 * */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByProperty(String property, Object value) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.where(cb.equal(root.get(property), value));
		return getEm().createQuery(cq).getResultList();
	}
	
	/**
	 * 
	 * property == Propriedade da Classe
	 * value == valor da property 
	 * begin == Posicao do primeiro elemento da busca
	 * end == Até quando vai percorer a busca
	 * 
	 * Exemplo:
	 * 
	 * List<Person> listPersonWithNameMarcos = dao.findByProperty("name", "Marcos", 3, 5);
	 * 
	 * */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByProperty(String property, Object value, int begin, int end) {
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		cq.where(cb.equal(root.get(property), value));
		return getEm().createQuery(cq).setFirstResult(begin).setMaxResults(end).getResultList();
	}
	
	
	
	/**
	 * 
	 * property == Propriedade da Classe
	 * value == valor da property 
	 * matchMode = START valor que inicia com o value, 
	 *             END valor que termina com o value, 
	 *             ANYWHERE valor que esteja entre valor do value
	 * Exemplo:
	 * 
	 * List<Person> listPersonWithNameMarcos = dao.findByProperty("name", "Souza", MatchMode.ANYWHERE);
	 * 
	 * */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByProperty(String propertyName, String value, MatchMode matchMode) {
		
		value = value.toLowerCase();
		
		if (MatchMode.START.equals(matchMode)) {
			value = value + "%";
		} else if (MatchMode.END.equals(matchMode)) {
			value = "%" + value;
		} else if (MatchMode.ANYWHERE.equals(matchMode)) {
			value = "%" + value + "%";
		}
		
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		Expression<String> expression =  root.get(propertyName);
		cq.where(cb.like(cb.lower(expression), value));
		
		return getEm().createQuery(cq).getResultList();
	}
	
	/**
	 * 
	 * property == Propriedade da Classe
	 * value == valor da property 
	 * matchMode = START valor que inicia com o value, 
	 *             END valor que termina com o value, 
	 *             ANYWHERE valor que esteja entre valor do value
	 *             
	 * begin == Posicao do primeiro elemento da busca
	 * end == Até quando vai percorer a busca
	 * Exemplo:
	 * 
	 * List<Person> listPersonWithNameMarcosFromThirdToEighth = dao.findByProperty("name", "Souza", MatchMode.ANYWHERE, 3, 8);
	 * 
	 * */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<T> findByProperty(String propertyName, String value, MatchMode matchMode, int begin, int end) {
		
		value = value.toLowerCase();
		
		if (MatchMode.START.equals(matchMode)) {
			value = value + "%";
		} else if (MatchMode.END.equals(matchMode)) {
			value = "%" + value;
		} else if (MatchMode.ANYWHERE.equals(matchMode)) {
			value = "%" + value + "%";
		}
		
		CriteriaBuilder cb = getEm().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(entityClass);
		Root<T> root = cq.from(entityClass);
		Expression<String> expression =  root.get(propertyName);
		cq.where(cb.like(cb.lower(expression), value));
		
		return getEm().createQuery(cq).setFirstResult(begin).setMaxResults(end).getResultList();
	}

   public EntityManager getEm() {
	   return em;
   }

   public void setEm(EntityManager em) {
	   this.em = em;
   }
	
	
}
