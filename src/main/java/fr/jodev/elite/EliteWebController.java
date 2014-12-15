package fr.jodev.elite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EliteWebController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String usage() {
		return "redirect:/pages/usage.html";
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
}
