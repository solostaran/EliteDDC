package fr.jodev.elite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.jodev.elite.exceptions.ExceptionObject;
import fr.jodev.elite.exceptions.SolarSystemNotFoundException;
import fr.jodev.elite.exceptions.StationNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlingController {
	
	protected Logger logger;

	public GlobalExceptionHandlingController() {
		logger = LoggerFactory.getLogger("ELITE EXCEPTION");
	}
	
	/**
	 * Exception handler to a dynamic Thymeleaf HTML page.
	 * @param req the request that provoked the error.
	 * @param exception the throwed java exception
	 * @return
	 * @throws Exception
	 */
//	@ExceptionHandler(SolarSystemNotFoundException.class)
//	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
//	public ModelAndView handleError(HttpServletRequest req, Exception exception)
//			throws Exception {
//		logger.error("Request: " + req.getRequestURI() + " raised " + exception);
//
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("message", exception.getMessage());
//		mav.addObject("url", req.getRequestURL());
//		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		mav.addObject("timestamp", df.format(new Date()));
//		mav.addObject("status", 400);
//
//		mav.setViewName("simpleException");
//		return mav;
//	}
	
	/**
	 * Exception handler to a JSON Exception Object.
	 * @param req the request that provoked the error.
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler({SolarSystemNotFoundException.class,StationNotFoundException.class})
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public @ResponseBody ExceptionObject handleErrorJson(HttpServletRequest req, Exception exception)
			throws Exception {
		ExceptionObject ret = new ExceptionObject();
		ret.reason = exception.getMessage();
		ret.errorCode = 400;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		ret.timestamp = df.format(new Date());
		ret.url = req.getRequestURL().toString();
		return ret;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleDefaultException(HttpServletRequest req, Exception exception)
			throws Exception {

		// Rethrow annotated exceptions or they will be processed here instead.
		if (AnnotationUtils.findAnnotation(exception.getClass(),
				ResponseStatus.class) != null)
			throw exception;

		logger.error("Request: " + req.getRequestURI() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("message", exception.getMessage());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);

		mav.setViewName("support");
		return mav;
	}
}
