package nl.amc.adict.sandbox.axon.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemAangemaaktEvent {

	private String id;

	private String omschrijving;
}
