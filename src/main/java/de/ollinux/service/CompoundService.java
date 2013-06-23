package de.ollinux.service;

import de.ollinux.messaging.Producer;
import de.ollinux.model.Person;
import de.ollinux.service.FooService;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * <a href="mailto:oliver.vesper@googlemail.com">Oliver Vesper</a>
 */
@Stateless
public class CompoundService {

    @Inject
    FooService fooService;

    @Inject
    Producer producer;

    public void operate(Person person) {
        fooService.foo(person);

        producer.send();

        // throw new RuntimeException("force rollback");
    }

}
