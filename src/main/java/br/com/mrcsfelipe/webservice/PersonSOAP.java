package br.com.mrcsfelipe.webservice;

import javax.ejb.EJB;
import javax.jws.WebService;

import br.com.mrcsfelipe.business.PersonBusinessFacede;
import br.com.mrcsfelipe.data.GenericRepositoryImpl.MatchMode;
import br.com.mrcsfelipe.data.GenericRepositoryImpl.Order;
import br.com.mrcsfelipe.model.Person;
import br.com.mrcsfelipe.rest.model.PersonsModel;

@WebService(serviceName = "PersonSOAP", 
			portName = "Person", 
			name = "Person", 
			endpointInterface = "br.com.mrcsfelipe.webservice.PersonSOAPInteface", //localizando a interface
			targetNamespace = "http://www.jboss.org/jbossas/quickstarts/wshelloworld/HelloWorld"
)
public class PersonSOAP implements PersonSOAPInteface {

	@EJB
	private PersonBusinessFacede personBusiness;

	public PersonsModel get() {
		PersonsModel p = new PersonsModel();
		p.setPersons(this.personBusiness.getPersons());
		return p;
	}

	public String createPerson(Person person) {
		System.out.println("Recebendo pelo SOAP");
		System.out.println(person.toString());
		this.personBusiness.save(person);
		return "salvoSucesso";
	}

	@Override
	public PersonsModel getPersonByOrderAndProperty(String order,
			String property) {
		
		PersonsModel p = new PersonsModel();
		if (order.equalsIgnoreCase("ASC")) {
			p.setPersons(this.personBusiness.getPersonByOrderAndProperty(Order.ASC,property));
		} else {
			p.setPersons(this.personBusiness.getPersonByOrderAndProperty(Order.DESC,property));
		}
		
		return p;
	}

	@Override
	public PersonsModel getPersonByOrderPropertyWithStartAndEnd(String order,
			String property, int start, int end) {
		PersonsModel p = new PersonsModel();
		if (order.equalsIgnoreCase("ASC")) {
			p.setPersons(this.personBusiness.getPersonByOrderPropertyWithStartAndEnd(Order.ASC, property, start, end));
		} else {
			p.setPersons(this.personBusiness.getPersonByOrderPropertyWithStartAndEnd(Order.DESC,property, start, end));
		}
		
		return p;
	}

	@Override
	public PersonsModel getPersonByProperty(String propertyName, String value,
			String matchMode, int begin, int end) {
		
		PersonsModel p = new PersonsModel();
		MatchMode matchModeEnum;
		if (matchMode.equalsIgnoreCase("START")) {
			matchModeEnum = MatchMode.START;
		} else if(matchMode.equalsIgnoreCase("END")){
			matchModeEnum = MatchMode.END;
		} else if(matchMode.equalsIgnoreCase("END")){
			matchModeEnum = MatchMode.ANYWHERE;
		}else {
			return p;
		}
		
		p.setPersons(this.personBusiness.getPersonByProperty(propertyName, value, matchModeEnum,  begin, end));
		
		return p;
	}

	

}
