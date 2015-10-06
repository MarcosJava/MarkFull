package br.com.mrcsfelipe.data;

import javax.ejb.Local;

import br.com.mrcsfelipe.model.Person;

@Local
public interface PersonJPA extends GenericRepository<Person, Integer> {

}
