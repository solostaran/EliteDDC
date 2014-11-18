package fr.jodev.elite.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.exceptions.EmptyArgumentException;
import fr.jodev.elite.services.SystemService;


@RestController
@RequestMapping("/solarsystem")
public class SolarSystemController {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("/byname")
	public List<SolarSystem> getByName(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		return systemService.getByName(name);
	}
	
	@RequestMapping("/create")
	public SolarSystem create(@RequestParam(value = "name", required = true) String name) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		return systemService.createSolarSystem(name);
	}

}
