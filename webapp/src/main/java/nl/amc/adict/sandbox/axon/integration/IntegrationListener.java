package nl.amc.adict.sandbox.axon.integration;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

import lombok.extern.slf4j.Slf4j;
import nl.amc.adict.sandbox.axon.api.ItemAangemaaktEvent;
import nl.amc.adict.sandbox.axon.api.ItemOmschrijvingGewijzigdEvent;

@Component
@Slf4j
public class IntegrationListener {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${out.exchange.name}")
	private String exchangeName;

	private XStream xstream = new XStream();

	@EventHandler
	public void handle(ItemAangemaaktEvent event) {
		log.info("Item aangemaakt met id {} en omschrijving: {}", event.getId(), event.getOmschrijving());
		amqpTemplate.convertAndSend(exchangeName, "", xstream.toXML(event));
	}

	@EventHandler
	public void handle(ItemOmschrijvingGewijzigdEvent event) {
		log.info("Item {} heeft nieuwe omschrijving: {}", event.getId(), event.getOmschrijving());
		amqpTemplate.convertAndSend(exchangeName, "", xstream.toXML(event));
	}
}
