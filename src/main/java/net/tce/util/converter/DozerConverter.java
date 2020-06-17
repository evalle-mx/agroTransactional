package net.tce.util.converter;

import java.util.Collections;
import java.util.Set;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

/**
 * Se especifica el uso de la herramienta Dozer como un GenericConverter
 * @author Goyo
 *
 */
public class DozerConverter implements GenericConverter, InitializingBean {

    
    /** Mapper Dozer */
    private Mapper mapper;


    // ---------------------------------------------------------------------
    // Implementation de GenericConverter
    // ---------------------------------------------------------------------

    /**
     * Un objeto se convierte en otro objeto
     */
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
    }

  /**
   * 
   */
    public Object convert(Object aSource, TypeDescriptor aSourceType, TypeDescriptor aTargetType) {
        return mapper.map(aSource, aTargetType.getType());
    }

  
    /**
     * Si la instancia no existe se crea.
     */
    public void afterPropertiesSet() {
        if (mapper == null) {
            mapper = new DozerBeanMapper();
        }
    }
 
    public void setMapper(final Mapper aMapper) {
        mapper = aMapper;
    }

    public Mapper getMapper() {
        return mapper;
    }

}
