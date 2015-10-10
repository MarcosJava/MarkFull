package br.com.mrcsfelipe.data;

import java.util.List;

import javax.ejb.Local;

import br.com.mrcsfelipe.model.Person;

@Local
public interface PersonJPA extends GenericRepository<Person, Integer> {
	public void savePersonsBulks(List<Person> persons);
}
