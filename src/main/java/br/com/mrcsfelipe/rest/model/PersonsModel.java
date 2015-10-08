package br.com.mrcsfelipe.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import br.com.mrcsfelipe.model.Person;

public class PersonsModel {
	
	
	private List<Person> persons = new ArrayList<>();
	
	
	public PersonsModel() {
		
	}
	
	@XmlElement(name="persons")
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
}
