import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class Sender {
	private final static String QUEUE_NAME = "hello";

	  public static void main(String[] argv) throws java.io.IOException {
		  
		  try {
		  	ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
		    
		    //Declaring a queue is idempotent - it will only be created if it doesn't exist already.
		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String message = "Hello World!";
		    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		    System.out.println(" [x] Sent '" + message + "'");
		    
		    channel.close();
		    connection.close();
		    
		  } catch (TimeoutException e) {
			  
		  }
	  }
}
