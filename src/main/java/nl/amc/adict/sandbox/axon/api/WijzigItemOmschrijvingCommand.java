package nl.amc.adict.sandbox.axon.api;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WijzigItemOmschrijvingCommand {

	@TargetAggregateIdentifier
	private String id;

	private String omschrijving;
}
