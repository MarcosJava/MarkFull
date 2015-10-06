package br.com.mrcsfelipe.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.com.mrcsfelipe.model.Person;

@XmlAccessorType(XmlAccessType.FIELD)
public class Personxmls {
	
	
	private List<Person> persons = new ArrayList<>();
	
	
	public Personxmls() {
		
	}
	
	@XmlElement(name="persons")
	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
}
