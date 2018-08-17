package nl.amc.adict.sandbox.axon.query.updaters;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.amc.adict.sandbox.axon.api.ItemAangemaaktEvent;
import nl.amc.adict.sandbox.axon.api.ItemOmschrijvingGewijzigdEvent;
import nl.amc.adict.sandbox.axon.query.model.Item;

@Component
public class ItemUpdater {

	@Autowired
	private EntityManager entityManager;

	@EventHandler
	public void handle(ItemAangemaaktEvent event) {
		entityManager.persist(new Item(event.getId(), event.getOmschrijving()));
	}

	@EventHandler
	public void handle(ItemOmschrijvingGewijzigdEvent event) {
		entityManager.find(Item.class, event.getId());
	}
}
