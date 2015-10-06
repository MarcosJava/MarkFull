package br.com.mrcsfelipe.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@TransactionManagement(TransactionManagementType.CONTAINER)
public abstract class GenericRepositoryImpl<T, ID extends Serializable> implements GenericRepository<T, ID>	 {

	@PersistenceContext(unitName="markFullOne")
	private EntityManager em;
	
	private Class<T> entityClass;
	
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
	
	public void delete(ID id, Class<T> classe){
		T entityToBeRemoved = getEm().getReference(classe, id);
		getEm().remove(entityToBeRemoved);
	}
	
	public T update(T entity){
		return getEm().merge(entity);
	}
	
	public T findByID(ID id){
		return getEm().find(entityClass, id);
	}
	
	// Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEm().createQuery(cq).getResultList();
    }
    
   @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll(int begin, int end) {
        CriteriaQuery cq = getEm().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEm().createQuery(cq).setFirstResult(begin).setMaxResults(end).getResultList();
    }

   public EntityManager getEm() {
	   return em;
   }

   public void setEm(EntityManager em) {
	   this.em = em;
   }
	
	
}
