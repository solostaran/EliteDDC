package fr.jodev.elite.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.EmptyArgumentException;
import fr.jodev.elite.services.StationService;

@RestController
@RequestMapping("/station")
public class StationController {
	@Autowired
	private StationService stationService;
	
	@RequestMapping("/byname")
	public List<Station> getByName(@RequestParam(value = "name", required = true) String name) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		return stationService.getByName(name);
	}
	
	@RequestMapping("/create")
	public Station create(@RequestParam(value="id",required=true) long idSolarSystem,
			@RequestParam(value="name",required=true) String name) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		return stationService.createStation(idSolarSystem, name);
	}
	
	@RequestMapping("/update")
	public void update(@RequestParam(value="id",required=true) long idStation,
			@RequestParam(value="name",required=true) String name,
			@RequestParam(value="market",required=true) boolean isMarket,
			@RequestParam(value="blackmarket",required=true) boolean isBlackMarket,
			@RequestParam(value="shipyard",required=true) boolean isShipyard,
			@RequestParam(value="outfitting",required=true) boolean isOutfitting) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		stationService.updateStation(idStation, name, isMarket, isBlackMarket, isShipyard, isOutfitting);
	}
}
