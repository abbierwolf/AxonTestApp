package nl.amc.adict.sandbox.axon.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemOmschrijvingGewijzigdEvent {

	private String id;

	private String omschrijving;
}
