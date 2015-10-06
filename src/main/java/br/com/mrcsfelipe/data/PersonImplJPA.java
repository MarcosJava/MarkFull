package br.com.mrcsfelipe.data;

import javax.ejb.Stateless;

import br.com.mrcsfelipe.model.Person;

@Stateless
public class PersonImplJPA extends GenericRepositoryImpl<Person, Integer> implements PersonJPA{

	public PersonImplJPA() {
		super(Person.class);
	}

}
