package fr.jodev.elite.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.jodev.elite.entities.Goods;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.EmptyArgumentException;
import fr.jodev.elite.services.GoodsService;
import fr.jodev.elite.services.StationService;

@RestController
@RequestMapping("/station")
public class StationController {
	@Autowired
	private StationService stationService;
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/byname")
	public List<Station> getByName(@RequestParam(value = "name", required = true) String name) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		return stationService.getByName(name);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public Station create(@RequestParam(value="id",required=true) long idSolarSystem,
			@RequestParam(value="name",required=true) String name) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		return stationService.createStation(idSolarSystem, name);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public Station create(@RequestBody fr.jodev.elite.model.Station station) {
		if (station.idSolarSystem == -1) {
			throw new EmptyArgumentException("idSolarSystem");
		}
		if (station.name == null || station.name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		stationService.createStation(station.idSolarSystem, station.name);
		stationService.updateStation(station);
		return null;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public void update(@RequestParam(value="id",required=true) long idStation,
			@RequestParam(value="name",required=false, defaultValue="") String name,
			@RequestParam(value="isMarket",required=false, defaultValue="") String market,
			@RequestParam(value="isBlackmarket",required=false, defaultValue="") String blackMarket,
			@RequestParam(value="isShipyard",required=false, defaultValue="") String shipyard,
			@RequestParam(value="isOutfitting",required=false, defaultValue="") String outfitting) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		fr.jodev.elite.model.Station station = new fr.jodev.elite.model.Station();
		station.idStation = idStation;
		station.name = name;
		station.isMarket = market;
		station.isBlackMarket = blackMarket;
		station.isShipyard = shipyard;
		station.isOutfitting = outfitting;
		stationService.updateStation(station);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public void update(@RequestBody fr.jodev.elite.model.Station station) {
		if (station.idStation == -1) {
			throw new EmptyArgumentException("idStation");
		}
		stationService.updateStation(station);
	}
	
	@RequestMapping("/shipyard")
	public List<Long> getShipyard(@RequestParam(value="idstation", required=true) long idStation) {
		return stationService.getShipBuyables(idStation);
	}
	
	@RequestMapping("/addship")
	public void addShipBuyable(@RequestParam(value="idstation", required=true) long idStation, @RequestParam(value="idship", required=true) long idShipBuyable) {
		stationService.addShipBuyable(idStation, idShipBuyable);
	}
	
	@RequestMapping("/removeship")
	public void removeShipBuyable(@RequestParam(value="idstation", required=true) long idStation, @RequestParam(value="idship", required=true) long idShipBuyable) {
		stationService.removeShipBuyable(idStation, idShipBuyable);
	}
	
	@RequestMapping("/{id}")
	public Station getStation(@PathVariable Long id) {
		return stationService.getById(id);
	}

	@RequestMapping("/{id}/shipyard")
	public List<Long> getShipyard(@PathVariable Long id) {
		return stationService.getShipBuyables(id);
	}
	
	@RequestMapping("/{id}/market")
	public List<Goods> getStationMarket(@PathVariable Long id) {
		return goodsService.getStationMarket(id);
	}
}
