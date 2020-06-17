package net.tce.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurationSupport;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MarshallingPayloadMethodProcessor;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Archivo de configuración para servicios SOAP con MTOM
 * @author dothr
 *
 */
@EnableWs
@Configuration
public class WebServiceSOAPConfig extends WsConfigurationSupport {
	
	@Value("${mapping.uri}")
	private String mapping_uri;

	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet,new StringBuilder(mapping_uri).
											append("*").toString());
	}

	@Bean(name = "files")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema documentsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("FileAdapterJAXWSImpl");
		wsdl11Definition.setLocationUri(mapping_uri);
		wsdl11Definition.setTargetNamespace("http://jaxws.adapter.admin.tce.net/");
		wsdl11Definition.setSchema(documentsSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema documentsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/spring/files.xsd"));
	}
	
	// ***********************
	// *******  MTOM  ********
	// ***********************

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("net.tce.admin.adapter.jaxws");
		marshaller.setMtomEnabled(true);
		return marshaller;
	}

	@Bean
	@Override
	public DefaultMethodEndpointAdapter defaultMethodEndpointAdapter() {
		List<MethodArgumentResolver> argumentResolvers =
				new ArrayList<MethodArgumentResolver>();
		argumentResolvers.add(methodProcessor());

		List<MethodReturnValueHandler> returnValueHandlers =
				new ArrayList<MethodReturnValueHandler>();
		returnValueHandlers.add(methodProcessor());

		DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
		adapter.setMethodArgumentResolvers(argumentResolvers);
		adapter.setMethodReturnValueHandlers(returnValueHandlers);

		return adapter;
	}

	@Bean
	public MarshallingPayloadMethodProcessor methodProcessor() {
		return new MarshallingPayloadMethodProcessor(marshaller());
	}

}
