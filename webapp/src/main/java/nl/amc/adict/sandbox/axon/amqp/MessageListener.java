package nl.amc.adict.sandbox.axon.amqp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

import nl.amc.adict.sandbox.axon.api.ItemAangemaaktEvent;
import nl.amc.adict.sandbox.axon.api.ItemOmschrijvingGewijzigdEvent;
import nl.amc.adict.sandbox.axon.query.updaters.ItemUpdater;

@Component
public class MessageListener {

	@Autowired
	private ItemUpdater itemUpdater;

	private XStream xstream = new XStream();

	@Transactional
	public void receive(String message) {
		Object event = xstream.fromXML(message);
		if (event instanceof ItemAangemaaktEvent) {
			itemUpdater.handle((ItemAangemaaktEvent) event);
		} else if (event instanceof ItemOmschrijvingGewijzigdEvent) {
			itemUpdater.handle((ItemOmschrijvingGewijzigdEvent) event);
		}
	}
}
