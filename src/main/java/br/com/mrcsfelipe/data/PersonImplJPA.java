package br.com.mrcsfelipe.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.mrcsfelipe.model.Person;

@Stateless
public class PersonImplJPA extends GenericRepositoryImpl<Person, Integer> implements PersonJPA{

	public PersonImplJPA() {
		super(Person.class);
	}

	@Override
	public void savePersonsBulks(List<Person> persons) {
	
		int i = 1;
		for(Person p : persons){
		  
		    save(p);
		    if ( i % 20 == 0 ) { //20, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		        getEm().flush();
		        getEm().clear();
		    }
		    i++;
		}
		
	}

}
