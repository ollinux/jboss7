package de.ollinux.rest;

import de.ollinux.service.CompoundService;
import de.ollinux.data.PersonRepository;
import de.ollinux.model.Person;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/persons")
@RequestScoped
public class Webservice {

    @Inject PersonRepository repository;
    @Inject CompoundService compoundService;

	@GET
	@Path("/event")
	@Produces(MediaType.APPLICATION_JSON)
    public de.ollinux.model.Person foo() {
        Person person = new de.ollinux.model.Person();
        person.setName("foobar");
        person.setEmail("foo@bar.com");
        person.getRoles().add("foo");
        person.getRoles().add("bar");
        person.getRoles().add("qwertz");

        try {
            compoundService.operate(person);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return person;
	}

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<de.ollinux.model.Person> findAll() {
        return repository.findAllOrderedByName();
    }

}
