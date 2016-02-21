/**
 * PersonSOAPInteface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.mrcsfelipe.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.mrcsfelipe.model.Person;
import br.com.mrcsfelipe.rest.model.PersonsModel;

@WebService
public interface PersonSOAPInteface {
	
	@WebMethod
	public PersonsModel get();

    @WebMethod
    public String createPerson(Person person);
    
    @WebMethod(operationName="gettingForTest")
    public PersonsModel getPersonByOrderAndProperty(@XmlElement(required=true)
    												@WebParam(name="order") String order, 
    												@WebParam(name="property") String property);
    
    @WebMethod
    public PersonsModel getPersonByOrderPropertyWithStartAndEnd(@WebParam(name="order") String order, 
    															@WebParam(name="property") String property, 
    															@WebParam(name="start") int start, 
    															@WebParam(name="end") int end);
    
    @WebMethod
    public PersonsModel getPersonByProperty(@WebParam(name="propertyName") String propertyName, 
    										@WebParam(name="value") String value, 
    										@WebParam(name="match") String matchMode, 
    										@WebParam(name="init")int begin, 
    										@WebParam(name="end")int end);
}
