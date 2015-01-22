package fr.jodev.elite.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.exceptions.EmptyArgumentException;
import fr.jodev.elite.model.Commodities2;
import fr.jodev.elite.services.GoodsService;
import fr.jodev.elite.services.SystemService;


@RestController
@RequestMapping("/solarsystem")
public class SolarSystemController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public @ResponseBody SolarSystem create(@RequestParam(value = "name", required = true) String name) {
		if (name == null || name.isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		return systemService.createSolarSystem(name);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody SolarSystem create(@RequestBody fr.jodev.elite.model.SolarSystem system) {
		if (system.getName() == null || system.getName().isEmpty()) {
			throw new EmptyArgumentException("name");
		}
		return systemService.createSolarSystem(system.getName(), system.getX(), system.getY(), system.getZ());
	}
	
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public @ResponseBody SolarSystem update(@RequestBody fr.jodev.elite.model.SolarSystem system) {
		if (system.getIdSolarSystem() == -1) {
			throw new EmptyArgumentException("idSolarSystem");
		}
		return systemService.updateSystem(system);
	}
	
	@RequestMapping("/byname")
	public @ResponseBody List<SolarSystem> getByName(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
//	public List<SolarSystem> getByName(@PathVariable String name) {
		return systemService.getByName(name);
	}
	
	@RequestMapping("/remove")
	public void removeById(@RequestParam(value = "id", required = true) Long id) {
		systemService.removeSolarSystemById(id);
	}
	
	@RequestMapping("/{id}")
//	public SolarSystem getById(@RequestParam(value = "id", required = true) Long id) {
	public @ResponseBody SolarSystem getById(@PathVariable Long id) {
		SolarSystem sys = systemService.getById(id);
		return sys;
	}
	
	@RequestMapping("/{id}/stations")
	public @ResponseBody List<Station> getStations(@PathVariable Long id) {
		return systemService.getStations(id);
	}
	
	@RequestMapping("/{id}/byprox")
	public @ResponseBody List<SolarSystem> getByProximity(@PathVariable Long id,
			@RequestParam(value="dist", required=true) float distance) {
		return systemService.getByProximity(id, distance);
	}
	
	@RequestMapping("/{id}/marketprox")
	public @ResponseBody List<Commodities2> getMarketByProximity(@PathVariable Long id,
			@RequestParam(value="dist", required=true) float distance) {
		return goodsService.getByProximity(id, distance);
	}
	
	@RequestMapping("/byprox")
	public @ResponseBody List<SolarSystem> getByProximity(@RequestBody fr.jodev.elite.model.SystemDistance sysdist) {
		return systemService.getByProximity(sysdist.idSolarSystem, sysdist.distance);
	}
	
	@RequestMapping("/marketprox")
	public @ResponseBody List<Commodities2> getMarketByProximity(@RequestBody fr.jodev.elite.model.SystemDistance sysdist) {
		return goodsService.getByProximity(sysdist.idSolarSystem, sysdist.distance);
	}
}
