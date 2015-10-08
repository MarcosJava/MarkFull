package br.com.mrcsfelipe.webservice;

import javax.ejb.EJB;
import javax.jws.WebService;

import br.com.mrcsfelipe.business.PersonBusinessFacede;
import br.com.mrcsfelipe.model.Person;
import br.com.mrcsfelipe.rest.model.PersonsModel;

@WebService(serviceName = "PersonSOAP", 
		    portName = "Person", 
		    name = "Person", 
		    endpointInterface = "br.com.mrcsfelipe.webservice.PersonSOAPInteface", //localizando a interface
		    targetNamespace = "http://www.jboss.org/jbossas/quickstarts/wshelloworld/HelloWorld"
		    )
public class PersonSOAP implements PersonSOAPInteface{

	@EJB
	private PersonBusinessFacede personBusiness;
	
  
    public PersonsModel get(){
    	PersonsModel p = new PersonsModel();
    	p.setPersons(this.personBusiness.getPersons());
    	return p;
    }

    public String createPerson(Person person){
    	System.out.println("Recebendo pelo SOAP");
    	System.out.println(person.toString());
    	this.personBusiness.save(person);
    	return "salvoSucesso";
    }

    
}
