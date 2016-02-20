package br.com.mrcsfelipe.data;

import java.io.Serializable;
import java.util.List;

import br.com.mrcsfelipe.data.GenericRepositoryImpl.MatchMode;
import br.com.mrcsfelipe.data.GenericRepositoryImpl.Order;

public interface GenericRepository <T, ID extends Serializable> {
	
	public void save(T entity);
	public void delete(ID id, Class<T> classe) throws Exception;
	public T update(T entity);
	public T findByID(ID id);
	public List<T> findAll();
	public List<T> findAll(int begin, int end);
	public List<T> findAll(Order order, String... propertiesOrder);
	public List<T> findAll(Order order, int begin, int end ,String... propertiesOrder);
	public List<T> findByProperty(String property, Object value);
	public List<T> findByProperty(String property, Object value, int begin, int end);
	public List<T> findByProperty(String propertyName, String value, MatchMode matchMode);
	public List<T> findByProperty(String propertyName, String value, MatchMode matchMode, int begin, int end);

}
