package br.com.mrcsfelipe.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.mrcsfelipe.data.GenericRepositoryImpl.MatchMode;
import br.com.mrcsfelipe.data.GenericRepositoryImpl.Order;
import br.com.mrcsfelipe.data.PersonJPA;
import br.com.mrcsfelipe.model.Person;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PersonBusinessFacede {
	
	@EJB
	private PersonJPA personJPA;
	
	public boolean hasField(Person person){
		if(person.getNome().equalsIgnoreCase("")){
			return false;
		} else if( person.getDtNascimento() == null){
			return false;
		}
		
		return true;
	}
	
	private boolean hasFieldWithId(Person person) {
		
		if (person.getId() == null || person.getId() == 0){
			return false;
		}
		
		return true;
	}	
	
	public void save(Person person){
		boolean complete = hasField(person);
		if(complete)
			this.personJPA.save(person);

	}
	
	public void deletar(Integer id) throws Exception{
		Person person = new Person();
		person.setId(id);
		boolean complete = hasFieldWithId(person);
		if(complete)
			this.personJPA.delete(person.getId(), Person.class);
	}
	public void deletar(Person person) throws Exception{
		boolean complete = hasFieldWithId(person);
		if(complete)
			this.personJPA.delete(person.getId(), Person.class);
	}
	
	

	public void bulkSave(List<Person> persons){
		for(Person p : persons){
			if(!hasField(p))
				return;
		}
		this.personJPA.savePersonsBulks(persons);
	}
	
	public List<Person> getPersons(){
		return this.personJPA.findAll();
	}

	public List<Person> getPersonByOrderAndProperty(Order asc, String property) {
		return this.personJPA.findAll(asc, property);
	}

	public List<Person> getPersonByOrderPropertyWithStartAndEnd(Order asc,
			String property, int start, int end) {
		return this.personJPA.findAll(asc, start, end, property);
	}

	public List<Person> getPersonByProperty(String propertyName, String value,
			MatchMode matchModeEnum, int begin, int end) {
		// TODO Auto-generated method stub
		return this.personJPA.findByProperty(propertyName, value, matchModeEnum, begin, end);
	}

}
