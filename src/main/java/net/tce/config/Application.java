/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.tce.config;

import net.tce.filter.AppFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author olm
 */
@Configuration
@ComponentScan("net.tce")
@PropertySource(value = { "classpath:application.properties" })
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	@Value("${contexto.app}")
	private String contexto_app;
	
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

	public static void main(String[] args) {
		// needed for streaming, see https://java.net/jira/browse/SAAJ-31
		System.setProperty("saaj.use.mimepull", "true");
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * Se asigna un contexto a la aplicacion. Se usa cuando se despliega la aplicación por medio de Spring-Boot
	 * en un tomcat Embebido. Comentar esta declaración cuando se despliega la aplicación en un Tomcat no embebido,
	 * ya que se toma el contexto del nombre del archivo war(AgroTransactional)
	 * @param dispatcherServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	    ServletRegistrationBean registration = new ServletRegistrationBean(
	            dispatcherServlet);
	    registration.addUrlMappings(contexto_app);
	    return registration;
	}
	
	/**
	 * Se adiciona un filtro 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean () {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    registrationBean.setFilter(new AppFilter());	    
	    return registrationBean;
	}
}
