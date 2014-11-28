package fr.jodev.elite;

import java.util.List;

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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
public class EliteAppConfig  extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(EliteAppConfig.class, args);
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

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
	
	@Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
