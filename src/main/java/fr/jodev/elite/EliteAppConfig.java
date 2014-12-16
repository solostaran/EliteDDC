package fr.jodev.elite;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
public class EliteAppConfig  extends WebMvcConfigurerAdapter {
	
	protected Logger logger;

	public static void main(String[] args) {
		SpringApplication.run(EliteAppConfig.class, args);
	}
	
	public EliteAppConfig() {
		logger = LoggerFactory.getLogger("ELITE APP CONFIG");
	}

	/* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
	 * to the MessageConverter and return it to be added to the HttpMessageConverters of our application
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	 * // do various things, perhaps:
	 * String someJsonString = mapper.writeValueAsString(someClassInstance);
	 * SomeClass someClassInstance = mapper.readValue(someJsonString, SomeClass.class)
	 */
	@Bean
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
		logger.info("Creating JSON Message Converter : "+MappingJackson2HttpMessageConverter.class.getName());
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

		ObjectMapper mapper = new ObjectMapper();
		// Registering Hibernate4Module to support lazy objects
		Hibernate4Module module = new Hibernate4Module();
		module.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
		mapper.registerModule(module);
		messageConverter.setObjectMapper(mapper);
		return messageConverter;

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//Here we add our custom-configured HttpMessageConverter
		converters.add(jacksonMessageConverter());
		super.configureMessageConverters(converters);
	}

	/**
	 * Equivalent for &lt;mvc:resources/&gt; tags
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/WEB-INF/templates/");
		registry.addResourceHandler("/pages/**").addResourceLocations("classpath:/WEB-INF/pages/");
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/WEB-INF/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/WEB-INF/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/WEB-INF/js/");
    }
	
	/**
	 * Equivalent for &lt;mvc:default-servlet-handler/&gt; tag
	 */
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
//	@Bean
//    public InternalResourceViewResolver getInternalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewNames("*jsp");
//        resolver.setOrder(2);
//        return resolver;
//    }
	
	/**
	 * Needed to modify the default Thymeleaf configuration.
	 * Default prefix = "/templates/"
	 * @see #getTemplateEngine()
	 */
//	@Bean(name="templateResolver")
//	public ServletContextTemplateResolver getTemplateResolver() {
//		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
//		resolver.setPrefix("/WEB-INF/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode("HTML5");
//		return resolver;
//	}
	
	/**
	 * Needed to modify the default Thymeleaf configuration.
	 * @see #getTemplateResolver()
	 * @see #getThymeleafViewResolver()
	 */
//	@Bean(name="templateEngine")
//	public SpringTemplateEngine getTemplateEngine() {
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setTemplateResolver(getTemplateResolver());
//		return engine;
//	}
	
	/**
	 * Needed to modify the default Thymeleaf configuration.
	 * @see #getTemplateEngine()
	 */
//	@Bean
//	public ThymeleafViewResolver getThymeleafViewResolver() {
//		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//		resolver.setTemplateEngine(getTemplateEngine());
////		resolver.setViewClass(ThymeleafView.class);
//		resolver.setOrder(2);
////		resolver.setViewNames(new String[] {"*.html","*.xhtml"});
//		resolver.setViewNames(new String[] {"*html"});
//		return resolver;
//	}
	
	/**
	 * Static pages view resolver. <br/>
	 * Not needed if Thymeleaf is included.
	 */
//	@Bean
//    public UrlBasedViewResolver getUrlViewResolver() {
//		logger.info("Creating static pages view resolver");
//		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setViewClass(org.springframework.web.servlet.view.tiles3.TilesView.class);
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".html");
//        resolver.setViewNames("*html");
//        resolver.setOrder(3);
//        return resolver;
//    }
	
	/**
	 * Setup the classic SimpleMappingExceptionResolver. This provides useful
	 * defaults for logging and handling exceptions. It has been part of Spring
	 * MVC since Spring V2.
	 * 
	 * Using a global exception handling controller instead.
	 * @see GlobalExceptionHandlingController
	 * 
	 * @return The new resolver
	 */
//	@Bean(name="simpleMappingExceptionResolver")
//    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
//		logger.info("Creating SimpleMappingExceptionResolver");
//        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
//
//        Properties mappings = new Properties();
//        mappings.setProperty(SolarSystemNotFoundException.class.getName(), "solarSystemNotFound");
//
//        r.setExceptionMappings(mappings);  // None by default
//        r.setDefaultErrorView("error");    // No default (error.html is the default template)
////        r.setExceptionAttribute("ex");     // Default is "exception"
//        r.setWarnLogCategory("ELITE.MvcLogger");     // No default
//        return r;
//    }
}
