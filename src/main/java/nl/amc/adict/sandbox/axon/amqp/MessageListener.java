package nl.amc.adict.sandbox.axon.amqp;

import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	public void receive(String message) {
		System.out.println(message);
	}
}
