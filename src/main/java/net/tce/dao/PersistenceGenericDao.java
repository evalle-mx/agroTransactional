package net.tce.dao;

import java.math.BigDecimal;
import java.util.List;

/**
 * Una interface generica para los DAO's
 * @author Goyo
 * @param <T>
 * @param <PK>
 */
public interface PersistenceGenericDao<T, PK > {
   
	/**
     * Crea un registro nuevo.
     *
     * @param id
     * @return el id del nuevo registro
     */
    Object create(T newInstance);

    /**
     * Recupera un objeto usando el id indicado como llave primaria.
     *
     * @param id
     * @return
     */
    T read(PK id);

    /**
     * Guarda los cambios hechos al objeto persistente.
     *
     * @param transientObject
     */
    void update(T transientObject);

    /**
     * Modifica o crea un objeto persistente.
     *
     * @param transientObject
     */
    void saveOrUpdate(T transientObject);

    
    /**
     * Remueve el objeto de la base de datos.
     *
     * @param persistentObject
     */
    void delete(T persistentObject);

    /**
     * Aplica la operacion merge en la sesion
     *
     * @param persistentObject
     * @return 
     */
    void merge(T persistentObject);
    
    /**
     * 
     */
    void flush();
    
    
    void clear();
    
    /**
     * Recupera una lista de los objetos correspondientes
     * @return
     */
    public List<T> findAll();
    
    /**
     * Recupera una lista de objetos usando varios filtros.
     * @param transientObject
     * @param id
     * @return
     */
    List<T> getByFilters(PK filtros);
    
    /**
     * Recupera una lista de objetos ejecutando la operaci�n between(SQL)
     * @param transientObject
     * @param filtros
     * @return
     */
    List<T> getByBetweenFilters(PK filtros);
    
    /**
     * Recupera una lista de objetos ejecutando 
     * (limitar su uso o deprecarlo)
     * @param hql
     * @return
     */
    List<T> getByQuery(PK hql);
    
    /**
     * Obtiene el siguiente valor de la secuencia dada
     * @param nombreSeq
     * @return
     */
     BigDecimal generaSecuencia(final PK nombreSeq);
    
     
     /**
      * 
      * @param sql
      * @return
      */
     List<T> getBySQLQuery(PK sql);
     
     /**
      * Borra el registro de la persona en la tabla dada
      * @param filtros, es un objeto map que contiene el idPersona y el nombre de la tabla
      */
    void deleteByPersona(PK id);
    
    /**
     * Borra el registro de la Empresa en la tabla dada
     * @param filtros, es un objeto map que contiene el idPersona y el nombre de la tabla
     */
   void deleteByEmpresa(PK id);
}