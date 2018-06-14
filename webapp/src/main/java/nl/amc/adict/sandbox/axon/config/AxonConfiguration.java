package nl.amc.adict.sandbox.axon.config;

import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.NoSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.amc.adict.sandbox.axon.command.model.Item;

@Configuration
public class AxonConfiguration {

	@Bean
	@Autowired
	public Repository<Item> itemRepository(ParameterResolverFactory parameterResolvingFactory, EventStore eventStore) {
		GenericAggregateFactory<Item> aggregateFactory = new GenericAggregateFactory<>(Item.class);
		return new EventSourcingRepository<>(aggregateFactory, eventStore, parameterResolvingFactory,
				NoSnapshotTriggerDefinition.INSTANCE);
	}
}
