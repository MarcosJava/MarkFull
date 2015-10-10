package br.com.mrcsfelipe.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.mrcsfelipe.business.PersonBusinessFacede;
import br.com.mrcsfelipe.model.Person;
import br.com.mrcsfelipe.rest.model.PersonsModel;

@Path("/persons")
@RequestScoped
public class PersonRest {
	
	@Inject
	PersonBusinessFacede personBusiness;
	
	@GET
    @Produces({"application/json"})
    public PersonsModel getPersons() {
		PersonsModel xml = new PersonsModel();
		xml.setPersons(personBusiness.getPersons());
        return xml;
    }
	
	@Path("/lot")
	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
	public Response createLotPersons(PersonsModel persons){
		
		boolean hasError = false;
		
		//For para validar todos
		for(Person p : persons.getPersons()){
			System.out.println(p.toString());
			
			if(p.getId() == 0) p.setId(null);
			
			boolean complete = isValideField(p);
			if(!complete){
				hasError = true;
			}
		}
		if(!hasError){
			//For para enviar
			this.personBusiness.bulkSave(persons.getPersons());
			return Response.accepted().build();
		}
		return Response.status(Status.BAD_REQUEST).entity("Campos Invalidos").build();
		
	}
	
	@POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    public Response createPerson(Person person){
		
		System.out.println(person.toString());
		
		if(person.getId() == 0) person.setId(null);
		
		boolean complete = isValideField(person);
		if(complete){
			this.personBusiness.save(person);
			return Response.accepted(person).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Campos Invalidos").build();
		}
		
		
	}
	
	
	
	
	public boolean isValideField(Person person){
		if(person.getNome().trim().equalsIgnoreCase("")){
			return false;
		} else if (person.getDtNascimento() == null){
			return false;
		}
		
		return true;
	}
}
