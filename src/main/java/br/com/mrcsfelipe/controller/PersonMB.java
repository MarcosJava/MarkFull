package br.com.mrcsfelipe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import br.com.mrcsfelipe.business.PersonBusinessFacede;
import br.com.mrcsfelipe.model.Person;

@ManagedBean
@RequestScoped
public class PersonMB {

	private Person person;
	private List<Person> persons;
	
	@EJB
	PersonBusinessFacede personBusiness;
	
	@PostConstruct
	public void init() {
		this.person = new Person();
		this.persons = new ArrayList<Person>();
	}
	
	public void cleanValues(){
		this.person = new Person();
	}
	
	public String createPerson(){
		
		personBusiness.save(person);
		cleanValues();
		return null;
	}
	
	public void deletar(){
		System.out.println(person.toString());
		personBusiness.deletar(person);
		cleanValues();
		getPersons();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Person> getPersons() {
		if(this.persons.size() == 0 ||
				this.persons == null){
			this.persons = this.personBusiness.getPersons();
		}
		return persons;
	}
	
	
	
}
