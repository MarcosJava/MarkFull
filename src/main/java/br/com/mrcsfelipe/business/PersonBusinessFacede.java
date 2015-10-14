package br.com.mrcsfelipe.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

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
	
	public void deletar(Integer id){
		Person person = new Person();
		person.setId(id);
		boolean complete = hasFieldWithId(person);
		if(complete)
			this.personJPA.delete(person.getId(), Person.class);
	}
	public void deletar(Person person){
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

}
