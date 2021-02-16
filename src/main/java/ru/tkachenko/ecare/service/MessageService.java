package ru.tkachenko.ecare.service;

import org.springframework.stereotype.Component;

import javax.jms.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import javax.jms.Queue;

@Component
public class MessageService {

    private static final String JMS_CONNECTION_FACTORY_JNDI = "jms/RemoteConnectionFactory";
    private static final String JMS_QUEUE_JNDI = "jms/queue/test";
    private static final String WILDFLY_REMOTING_URL = "http-remoting://localhost:8080";
    private static final String JMS_USERNAME = "ecare";
    private static final String JMS_PASS = "ecare22";

    public void sendMessage(String message) throws NamingException, JMSException {


        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        properties.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);
        properties.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
        properties.put(Context.SECURITY_CREDENTIALS, JMS_PASS);

        Context context = new InitialContext(properties);
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(JMS_CONNECTION_FACTORY_JNDI);
        Queue queue = (Queue) context.lookup(JMS_QUEUE_JNDI);

        QueueConnection connection = connectionFactory.createQueueConnection(JMS_USERNAME, JMS_PASS);
        connection.start();
            QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(queue);

            TextMessage textMessage = session.createTextMessage(message);
            sender.send(textMessage);

            sender.close();
            session.close();
            connection.close();

    }
}
