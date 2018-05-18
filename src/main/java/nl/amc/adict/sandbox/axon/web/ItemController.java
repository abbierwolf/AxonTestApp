package nl.amc.adict.sandbox.axon.web;

import java.util.Optional;
import java.util.UUID;

import javax.sql.DataSource;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventhandling.EventProcessor;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nl.amc.adict.sandbox.axon.api.MaakItemAanCommand;
import nl.amc.adict.sandbox.axon.query.repositories.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	private CommandGateway commandGateway;

	@Autowired
	private EventHandlingConfiguration eventHandlingConfiguration;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("items", itemRepository.findAll());
		return "index";
	}

	@PostMapping("/nieuw")
	public String nieuw(@RequestParam String omschrijving, Model model) {
		commandGateway.sendAndWait(new MaakItemAanCommand(UUID.randomUUID().toString(), omschrijving));
		return "redirect:/";
	}

	@GetMapping("/reset")
	public String reset() {
		String processorName = "nl.amc.adict.sandbox.axon.integration";
		Optional<EventProcessor> optionalProcessor = eventHandlingConfiguration.getProcessor(processorName);
		if (optionalProcessor.isPresent()) {
			TrackingEventProcessor eventProcessor = (TrackingEventProcessor) optionalProcessor.get();
			System.out.println("-- " + eventProcessor);
			eventProcessor.shutDown();

			JdbcTemplate jt = new JdbcTemplate(dataSource);
			jt.update("DELETE FROM [es].[tblTokenEntry] WHERE processorname = ?", processorName);

			eventProcessor.start();
		}
		return "redirect:/";
	}
}
