package de.ollinux.service;

import de.ollinux.annotations.Second;
import de.ollinux.model.Person;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * <a href="mailto:oliver.vesper@googlemail.com">Oliver Vesper</a>
 */
@Stateless
public class FooService {

    @Inject Event<Person> event;
    @Inject @Second EntityManager em;

    public void foo(Person person) {
        System.out.println("--> fire person event");
        em.persist(person);
        event.fire(person);
        System.out.println("<-- fire person event");
    }

}
