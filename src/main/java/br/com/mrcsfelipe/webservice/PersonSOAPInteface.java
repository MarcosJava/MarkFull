package br.com.mrcsfelipe.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.mrcsfelipe.model.Person;
import br.com.mrcsfelipe.rest.model.PersonsModel;

@WebService
public interface PersonSOAPInteface {
	
	@WebMethod
	public PersonsModel get();

    @WebMethod
    public String createPerson(Person person);

}
