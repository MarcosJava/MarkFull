package br.com.mrcsfelipe.testing;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.mrcsfelipe.business.PersonBusinessFacede;
import br.com.mrcsfelipe.model.Person;

@RunWith(Arquillian.class)
public class PersonTest {

	
	@Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Person.class, PersonBusinessFacede.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

    @EJB
    PersonBusinessFacede personBusiness;

    

    @Test
    public void testRegister() throws Exception {
    	
    	Person newPerson = new Person();
    	newPerson.setNome("Marcos");
    	newPerson.setDtNascimento(new Date());
    	
    	personBusiness.save(newPerson);
    	

        assertNotNull(newPerson.getId());
        
    }

}
