package de.ollinux.messaging

import javax.jms.Destination

/**
 * <a href="mailto:oliver.vesper@googlemail.com">Oliver Vesper</a>
 */
public interface Jms {

    public <T extends Serializable> void send(Destination destination, T... objects)

}
