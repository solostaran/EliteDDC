package fr.jodev.elite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.services.ShipBuyableService;
import fr.jodev.elite.services.SystemService;

@Controller
public class EliteWebController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ShipBuyableService shipBuyableService;
	
	private List<SolarSystem> listSystems;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String usage() {
		return "redirect:/pages/usage.html";
	}

	@RequestMapping(value="/html", method=RequestMethod.GET)
	public String html() {
		return "redirect:/pages/menu.html";
	}
	
	@RequestMapping(value="/web/findsystem")
	public ModelAndView findSolarSystem(
			@RequestParam(value="name", required=false, defaultValue="$") String name,
			@RequestParam(value="web", required=false, defaultValue="") String web,
			@RequestParam(value="json", required=false, defaultValue="") String json) {
		ModelAndView mav;
		if (!json.isEmpty()) {
			mav = new ModelAndView("redirect:/solarsystem/byname");
			mav.addObject("name", name);
		}
		else {
			if ("$".equals(name)) {
				listSystems = null;
			} else {
				listSystems = systemService.getByName(name);
			}
			mav = new ModelAndView("findSystem");
			mav.addObject("systems", listSystems);
		}
	    return mav;
	}
	
	@RequestMapping(value="/web/allships")
	public ModelAndView allShips() {
		ModelAndView mav = new ModelAndView("allShips");
	    mav.addObject("ships", shipBuyableService.getAll());
	    return mav;
	}

//	@RequestMapping("/error.html")
//	public String error(HttpServletRequest request, Model model) {
//		System.out.println(">>>>>>>>>>>>> Il est passé par ici !");
//		model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
//		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
//		String errorMessage = null;
//		if (throwable != null) {
//			errorMessage = throwable.getMessage();
//		}
//		model.addAttribute("errorMessage", errorMessage.toString());
//		return "error.html";
//	}
	
//	@ModelAttribute("allships")
//	public List<ShipBuyable> populateSystems() {
//		return shipBuyableService.getAll();
//	}
}