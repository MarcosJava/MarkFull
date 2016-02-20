/**
 * PersonSOAPInteface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

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
