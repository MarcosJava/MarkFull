package br.com.mrcsfelipe.data;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository <T, ID extends Serializable> {
	
	public void save(T entity);
	public void delete(ID id, Class<T> classe);
	public T update(T entity);
	public T findByID(ID id);
	public List<T> findAll();
	public List<T> findAll(int begin, int end);

}
