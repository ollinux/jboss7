package de.ollinux.messaging

import javax.annotation.Resource
import javax.ejb.Local
import javax.ejb.Stateless
import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.Destination
import javax.jms.JMSException
import javax.jms.MessageProducer
import javax.jms.ObjectMessage
import javax.jms.Session

/**
 * <a href="mailto:oliver.vesper@googlemail.com">Oliver Vesper</a>
 */
@Stateless
@Local(Jms)
class JmsTemplate implements Jms {

    @Resource(mappedName = "java:/JmsXA") static ConnectionFactory connectionFactory

    @Override
    public <T extends Serializable> void send(Destination destination, T... objects) {
        assert destination
        assert objects
        assert objects.size() > 0

        Connection connection = null
        try {
            connection = connectionFactory.createConnection()
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE)
            MessageProducer messageProducer = session.createProducer(destination)

            for (T t : objects) {
                ObjectMessage message = session.createObjectMessage()
                message.setObject(t)
                println ("send message: " + t)
                messageProducer.send(message)
            }
        } catch (JMSException e) {
            throw new RuntimeException("failed to send message", e)
        } finally {
            if (null != connection) {
                try {
                    connection.close()
                } catch (Exception e) {
                }
            }
        }
    }

}
