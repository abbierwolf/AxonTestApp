package nl.amc.adict.sandbox.axon.command.model;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import nl.amc.adict.sandbox.axon.api.ItemAangemaaktEvent;
import nl.amc.adict.sandbox.axon.api.ItemOmschrijvingGewijzigdEvent;
import nl.amc.adict.sandbox.axon.api.MaakItemAanCommand;
import nl.amc.adict.sandbox.axon.api.WijzigItemOmschrijvingCommand;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Aggregate
public class Item {

	@AggregateIdentifier
	private String id;
	private String omschrijving;

	@CommandHandler
	public Item(MaakItemAanCommand command) {
		super();
		apply(new ItemAangemaaktEvent(command.getId(), command.getOmschrijving()));
	}

	@CommandHandler
	public void wijzigOmschrijving(WijzigItemOmschrijvingCommand command) {
		if (!StringUtils.equals(this.omschrijving, command.getOmschrijving())) {
			apply(new ItemOmschrijvingGewijzigdEvent(command.getId(), command.getOmschrijving()));
		}
	}

	@EventSourcingHandler
	private void handle(ItemAangemaaktEvent event) {
		this.id = event.getId();
		this.omschrijving = event.getOmschrijving();
	}

	@EventSourcingHandler
	private void handle(ItemOmschrijvingGewijzigdEvent event) {
		this.omschrijving = event.getOmschrijving();
	}
}
