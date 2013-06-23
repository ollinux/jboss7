package de.ollinux.messaging;

import de.ollinux.annotations.Second;
import de.ollinux.model.Person;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Queue;
import javax.persistence.EntityManager;

/**
 * <a href="mailto:oliver.vesper@googlemail.com">Oliver Vesper</a>
 */
@Stateless
public class Producer {

    @Resource(mappedName = "java:/queue/test") Queue queue;

    @Inject Jms jms;

    @Inject @Second EntityManager em;

    public void send() {
        Person person1 = new Person();
        person1.setEmail("one@foo.com");
        person1.setName("one");
        person1.getRoles().add("asdf");

        em.persist(person1);

        Person person2 = new Person();
        person2.setEmail("two@foo.com");
        person2.setName("two");
        person2.getRoles().add("qwertz");

        em.persist(person2);

        jms.send(queue, person1, person2);
    }
}
