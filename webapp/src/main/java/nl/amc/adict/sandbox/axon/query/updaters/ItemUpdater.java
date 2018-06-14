package nl.amc.adict.sandbox.axon.query.updaters;

import javax.persistence.EntityManager;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.amc.adict.sandbox.axon.api.ItemAangemaaktEvent;
import nl.amc.adict.sandbox.axon.api.ItemOmschrijvingGewijzigdEvent;
import nl.amc.adict.sandbox.axon.query.model.Item;
import nl.amc.adict.sandbox.axon.query.repositories.ItemQueryRepository;

@Component
public class ItemUpdater {

	@Autowired
	private EntityManager entityManager;

	@EventHandler
	public void handle(ItemAangemaaktEvent event, ItemQueryRepository itemQueryRepository) {
		entityManager.persist(new Item(event.getId(), event.getOmschrijving()));
		System.out.println(itemQueryRepository.count());
	}

	@EventHandler
	public void handle(ItemOmschrijvingGewijzigdEvent event) {
		entityManager.find(Item.class, event.getId());
	}
}
