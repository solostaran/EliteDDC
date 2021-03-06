package fr.jodev.elite;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.jodev.elite.entities.SolarSystem;
import fr.jodev.elite.entities.Station;
import fr.jodev.elite.model.Commodities2;
import fr.jodev.elite.model.CsvParsingErrors;
import fr.jodev.elite.model.Priority;
import fr.jodev.elite.model.SupplyOrDemand;
import fr.jodev.elite.services.GoodsCategoryService;
import fr.jodev.elite.services.GoodsDesignationService;
import fr.jodev.elite.services.GoodsService;
import fr.jodev.elite.services.ShipBuyableService;
import fr.jodev.elite.services.StationService;
import fr.jodev.elite.services.SystemService;

@Controller
public class EliteWebController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private ShipBuyableService shipBuyableService;
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	
	@Autowired
	private GoodsDesignationService goodsDesignationService;
	
	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String usage() {
		return "redirect:/pages/usage.html";
	}

	@RequestMapping(value="/html", method=RequestMethod.GET)
	public String html() {
		return "redirect:/pages/menu.html";
	}
	
	@RequestMapping(value="/html/allships")
	public ModelAndView allShips() {
		ModelAndView mav = new ModelAndView("allShips");
	    mav.addObject("ships", shipBuyableService.getAll());
	    return mav;
	}
	
	@RequestMapping(value="/html/allsystems")
	public ModelAndView allSystems() {
		ModelAndView mav = new ModelAndView("allSystems");
	    List<SolarSystem> list = systemService.getByName("");
	    mav.addObject("systems", list);
	    return mav;
	}
	
	@RequestMapping(value="/html/findsystem")
	public ModelAndView findSolarSystem(
			@RequestParam(value="name", required=false, defaultValue="") String name,
			@RequestParam(value="web", required=false, defaultValue="") String web,
			@RequestParam(value="json", required=false, defaultValue="") String json) {
		ModelAndView mav;
		if (!json.isEmpty()) {
			mav = new ModelAndView("redirect:/solarsystem/byname");
			mav.addObject("name", name);
		}
		else {
			List<SolarSystem> listSystems = null;
			if (name.isEmpty()) {
//				listSystems = systemService.getByName(name);
				listSystems = null;
			} else {
				listSystems = systemService.getByName(name);
			}
			if (listSystems != null && listSystems.size() == 1) {
				mav = new ModelAndView("showSystem");
				SolarSystem sys = listSystems.get(0);
				mav.addObject("system", sys);
				mav.addObject("stations", systemService.getStations(sys.getIdSolarSystem()));
			} else {
				mav = new ModelAndView("findSystem");
				mav.addObject("systems", listSystems);
				mav.addObject("reqname",name);
			}
		}
	    return mav;
	}
	
	@RequestMapping(value="/html/searchsystemprox")
	public ModelAndView searchSystemsByProximity(
			@RequestParam(value="distance", required=false, defaultValue="") Long distance,
			@RequestParam(value="idSolarSystem", required=false, defaultValue="") Long idSolarSystem,
			@RequestParam(value="reqname", required=false, defaultValue="") String reqname) {
		List<SolarSystem> listSystems = systemService.getByProximity(idSolarSystem, distance);
		SolarSystem sys = systemService.getById(idSolarSystem);
		ModelAndView mav = new ModelAndView("findSystem");
		mav.addObject("systems", listSystems);
		mav.addObject("system", sys);
		mav.addObject("distance", distance);
		return mav;
	}
	
	@RequestMapping(value="/html/showsystem/{id}")
	public ModelAndView showSolarSystem(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("showSystem");
		mav.addObject("system", systemService.getById(id));
		mav.addObject("stations", systemService.getStations(id));
		return mav;
	}
	
	@RequestMapping(value="/html/createsystem")
	public ModelAndView createSolarSystem(
			@RequestParam(value="name", required=true) String name) {
		ModelAndView mav = null;
		List<SolarSystem> listSystems = systemService.getByName(name);
		if (name == null || name.isEmpty() || listSystems.size() > 0) {
			mav = new ModelAndView("findSystem");
			mav.addObject("systems", listSystems);
			mav.addObject("reqname", name);
		} else {
			mav = new ModelAndView("showSystem");
			SolarSystem sys = systemService.createSolarSystem(name);
			mav.addObject("system", sys);
			mav.addObject("stations", systemService.getStations(sys.getIdSolarSystem()));
			mav.addObject("created", DateNumberSerializer.getDate());
		}
		return mav;
	}
	
	@RequestMapping(value="/html/updatesystem")
	public ModelAndView updateSolarSystem(
			final fr.jodev.elite.model.SolarSystem system) {
		SolarSystem sys = systemService.updateSystem(system);
		ModelAndView mav = new ModelAndView("showSystem");
		mav.addObject("system", sys);
		mav.addObject("stations", systemService.getStations(sys.getIdSolarSystem()));
		mav.addObject("updated", DateNumberSerializer.getDate());
		return mav;
	}
	
	@RequestMapping(value="/html/removesystem")
	public ModelAndView removeSolarSystem(@RequestParam(value="id", required=true) Long id) {
		systemService.removeSolarSystemById(id);
		ModelAndView mav = new ModelAndView("findSystem");
		return mav;
	}
	
	@RequestMapping(value="/html/showstation/{id}")
	public ModelAndView showStation(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("showStation");
		Station sta = stationService.getById(id);
		mav.addObject("station", sta);
		mav.addObject("system", sta.getParentSolarSystem());
		return mav;
	}
	
	@RequestMapping(value="/html/createstation")
	public ModelAndView createStation(
			@RequestParam(value="parentSolarSystem", required=true) Long id,
			@RequestParam(value="name", required=true) String name) {
		Station sta = stationService.createStation(id, name);
		ModelAndView mav = new ModelAndView("showStation");
		mav.addObject("station", sta);
		mav.addObject("system", sta.getParentSolarSystem());
		mav.addObject("created", DateNumberSerializer.getDate());
		return mav;
	}
	
	@RequestMapping(value="/html/updatestation")
	public ModelAndView updateStation(
			final fr.jodev.elite.model.Station station) {
		System.out.println("id="+station.idStation+",parent="+station.parentSolarSystem);
		Station sta = stationService.updateStation(station);
		ModelAndView mav = new ModelAndView("showStation");
		mav.addObject("station", sta);
		mav.addObject("system", sta.getParentSolarSystem());
		mav.addObject("updated", DateNumberSerializer.getDate());
		return mav;
	}
	
	@RequestMapping(value="/html/removestation")
	public ModelAndView removeStation(@RequestParam(value="id", required=true) Long id) {
		long idSystem = stationService.getById(id).getParentSolarSystem().getIdSolarSystem();
		stationService.removeStationById(id);
		ModelAndView mav = new ModelAndView("showSystem");
		mav.addObject("system", systemService.getById(idSystem));
		mav.addObject("stations", systemService.getStations(idSystem));
		return mav;
	}
	
	@RequestMapping(value="/html/showmarket/{id}")
	public ModelAndView showMarket(@PathVariable Long id) {
		Station sta = stationService.getById(id);
		ModelAndView mav = new ModelAndView("showMarket");
		mav.addObject("station", sta);
		mav.addObject("market", goodsService.getStationMarketFull(id));
		mav.addObject("categories", goodsCategoryService.getAll());
		mav.addObject("designations", goodsDesignationService.getAll());
		return mav;
	}
	
	@RequestMapping(value="/html/showcommodities/{id}")
	public ModelAndView showCommodities(@PathVariable Long id) {
//		Station sta = stationService.getById(id);
		ModelAndView mav = new ModelAndView("showCommodities");
//		mav.addObject("station", sta);
		mav.addObject("market", goodsService.getCommodities2(id));
		mav.addObject("categories", goodsCategoryService.getAll());
		mav.addObject("designations", goodsDesignationService.getAll());
		return mav;
	}
	
	@RequestMapping(value="/html/comparemarkets")
	public ModelAndView compareMarkets(
			@RequestParam(value="idSolarSystem", required=true) Long idSolarSystem,
			@RequestParam(value="distance", required=true) float distance) {
		List<Commodities2> commodities = goodsService.getByProximity(idSolarSystem, distance);
		ModelAndView mav = new ModelAndView("compareMarkets");
		mav.addObject("markets", commodities);
		mav.addObject("categories", goodsCategoryService.getAll());
		mav.addObject("designations", goodsDesignationService.getAll());
		return mav;
	}
	
	@RequestMapping(value="/html/updatemarket")
	public ModelAndView updateStation(
			final fr.jodev.elite.model.StationMarket market) {
		goodsService.updateGoods(market);
		ModelAndView mav = new ModelAndView("showMarket");
		Station sta = stationService.getById(market.idStation);
		mav.addObject("station", sta);
		mav.addObject("market", goodsService.getStationMarketFull(market.idStation));
		mav.addObject("categories", goodsCategoryService.getAll());
		mav.addObject("designations", goodsDesignationService.getAll());
		mav.addObject("updated", DateNumberSerializer.getDate());
		return mav;
	}
	
	@RequestMapping(value="/html/updatecommodities2")
	public ModelAndView updateCommodities2(
			final fr.jodev.elite.model.Commodities2 market) {
		goodsService.updateGoods(market);
		ModelAndView mav = new ModelAndView("showCommodities");
		Station sta = stationService.getById(market.idStation);
		mav.addObject("station", sta);
		mav.addObject("market", goodsService.getCommodities2(market.idStation));
		mav.addObject("categories", goodsCategoryService.getAll());
		mav.addObject("designations", goodsDesignationService.getAll());
		mav.addObject("updated", DateNumberSerializer.getDate());
		return mav;
	}
	
	@RequestMapping(value="/html/showshipyard/{id}")
	public ModelAndView showShipyard(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("showShipyard");
		mav.addObject("shipyard", stationService.getShipyard(id));
		mav.addObject("shipsbuyable", shipBuyableService.getAll());
		return mav;
	}
	
	@RequestMapping(value="/html/updateshipyard")
	public ModelAndView updateShipyard(final fr.jodev.elite.model.StationShipyard shipyard) {
		ModelAndView mav = new ModelAndView("showShipyard");
		mav.addObject("shipyard", stationService.updateShipyard(shipyard));
		mav.addObject("shipsbuyable", shipBuyableService.getAll());
		mav.addObject("updated", DateNumberSerializer.getDate());
		return mav;
	}
	
	@RequestMapping(value="/html/uploadocr")
	public ModelAndView uploadOcrFile() {
		ModelAndView mav = new ModelAndView("uploadocr");
		return mav;
	}
	
	private static Logger logger = Logger.getLogger("ELITE");
	
	@RequestMapping(value="/html/uploadFile", method = RequestMethod.POST)
	public ModelAndView htmlUploadFileHandler(@RequestParam("file") MultipartFile file) {
		ModelAndView mav = new ModelAndView("uploadocr");
		mav.addObject("csvparsingerrors", storeAndParseCsvFile(file));
		return mav;
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody CsvParsingErrors uploadFileHandler(@RequestParam("file") MultipartFile file) {
		return storeAndParseCsvFile(file);
    }
	/**
	 * Sequence number for filenames, it manages concurrency
	 */
	private AtomicInteger numseq = new AtomicInteger(1);
	
	private CsvParsingErrors storeAndParseCsvFile(MultipartFile file) {
		CsvParsingErrors ret = new CsvParsingErrors();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
 
                // Creating the directory to store file
                String rootPath = System.getProperty("TEMP");
                
                if (rootPath == null) rootPath = new File("D:\\temp\\encode").getAbsolutePath().toString();
                else logger.info("Temporary files directory="+rootPath);
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();
                
                // Filename
                DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String filename = "FileToProcess-" + df.format(new Date()) + "-"
	                + numseq.getAndIncrement() + "-" + file.getOriginalFilename();
 
                // Create the file on server
                final File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + filename);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
 
                logger.info("OCR import file : " + serverFile.getAbsolutePath());
                
                if (filename.endsWith(".csv")) {
//	                new Thread(new Runnable()  {
//						@Override
//						public void run() {
							ret = goodsService.updateGoods(serverFile);
//						}
//	                }).start();
                } else {
                	serverFile.delete();
                }
 
                ret.setMessage("You successfully uploaded file=" + file.getOriginalFilename());
            } catch (Exception e) {
                ret.setMessage("You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
            }
        } else {
           ret.setMessage("You failed to upload " + file.getOriginalFilename() + " because the file was empty.");
        }
        return ret;
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
	
	private static final List<SupplyOrDemand> listSoD = Arrays.asList(SupplyOrDemand.ALL);
	
	@ModelAttribute("allSoD")
	public List<SupplyOrDemand> populateSoD() {
		return listSoD;
	}
	
	private static final List<Priority> listPrio = Arrays.asList(Priority.ALL);
	
	@ModelAttribute("allPriorities")
	public List<Priority> populatePriorities() {
		return listPrio;
	}
}