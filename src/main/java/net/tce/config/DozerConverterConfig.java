package net.tce.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.tce.util.converter.DozerConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

@Configuration 
public class DozerConverterConfig {

	 @Bean
	    public ConversionServiceFactoryBean converter() {
	        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
	        Set<DozerConverter> converters = new HashSet<DozerConverter>();
	        DozerConverter dozerConverter=new DozerConverter();
	        DozerBeanMapper mapper = new DozerBeanMapper();
	        List<String> myMappingFiles = new ArrayList<String>();
	        myMappingFiles.add("dozer/dozer-applicant-mapping.xml");
	        myMappingFiles.add("dozer/dozer-catalogos-mapping.xml");
	        myMappingFiles.add("dozer/dozer-curriculum-mapping.xml");
	        myMappingFiles.add("dozer/dozer-employer-mapping.xml");
	        myMappingFiles.add("dozer/dozer-enterprise-mapping.xml");
	        myMappingFiles.add("dozer/dozer-file-mapping.xml");
	        myMappingFiles.add("dozer/dozer-notification-mapping.xml");
	        myMappingFiles.add("dozer/dozer-solrdocs-mapping.xml");  
	        myMappingFiles.add("dozer/dozer-compensation-mapping.xml");        
	        mapper.setMappingFiles(myMappingFiles);
	        dozerConverter.setMapper(mapper);	        
	        converters.add(dozerConverter);
	        factoryBean.setConverters(converters);
	        return factoryBean;
	    }
}
