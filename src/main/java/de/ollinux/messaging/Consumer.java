package de.ollinux.messaging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;

/**
 * <a href="mailto:oliver.vesper@googlemail.com">Oliver Vesper</a>
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "AUTO_ACKNOWLEDGE")
})
public class Consumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            try {
                System.out.println("objectmessage received: " + ((ObjectMessage)message).getObject());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("message received: " + message);
        }
    }
}
