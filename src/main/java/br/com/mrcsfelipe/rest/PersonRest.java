package br.com.mrcsfelipe.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.mrcsfelipe.business.PersonBusinessFacede;
import br.com.mrcsfelipe.model.Person;

@Path("/persons")
@RequestScoped
public class PersonRest {
	
	@EJB
	PersonBusinessFacede personBusiness;
	
	@GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Personxmls listAllMembers() {
		Personxmls xml = new Personxmls();
		xml.setPersons(personBusiness.getPersons());
        return xml;
    }
	
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(Person person){
		 
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
