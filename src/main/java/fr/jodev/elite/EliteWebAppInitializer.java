package fr.jodev.elite;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import fr.jodev.elite.entities.HibernateConfiguration;

public class EliteWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(EliteAppConfig.class);
		ctx.register(HibernateConfiguration.class);
		ctx.setServletContext(servletContext);
		
		// Multipart file upload
//		MultipartConfigElement config = new MultipartConfigElement("D:\\temp\\encode", 20848820, 418018841, 1048576);
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
		
		Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		dynamic.addMapping("/");
		dynamic.setLoadOnStartup(1);
		
		dynamic.setMultipartConfig(new MultipartConfigElement("D:\\temp\\encode", 1024*1024*2, 1024*1024*4, 1024*1024));
	}

}
