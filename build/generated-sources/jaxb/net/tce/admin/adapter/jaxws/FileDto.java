//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.15 at 02:17:03 PM EDT 
//


package net.tce.admin.adapter.jaxws;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fileDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idEmpresaConf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dhContenido" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="idContenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idPersona" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idTipoContenido" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoArchivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileDto", propOrder = {
    "idEmpresaConf",
    "descripcion",
    "dhContenido",
    "idContenido",
    "idEmpresa",
    "idPersona",
    "idTipoContenido",
    "tipoArchivo"
})
public class FileDto {

    protected String idEmpresaConf;
    protected String descripcion;
    @XmlMimeType("application/octet-stream")
    protected DataHandler dhContenido;
    protected String idContenido;
    protected String idEmpresa;
    protected String idPersona;
    protected String idTipoContenido;
    protected String tipoArchivo;

    /**
     * Gets the value of the idEmpresaConf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEmpresaConf() {
        return idEmpresaConf;
    }

    /**
     * Sets the value of the idEmpresaConf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEmpresaConf(String value) {
        this.idEmpresaConf = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the dhContenido property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getDhContenido() {
        return dhContenido;
    }

    /**
     * Sets the value of the dhContenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setDhContenido(DataHandler value) {
        this.dhContenido = value;
    }

    /**
     * Gets the value of the idContenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdContenido() {
        return idContenido;
    }

    /**
     * Sets the value of the idContenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdContenido(String value) {
        this.idContenido = value;
    }

    /**
     * Gets the value of the idEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * Sets the value of the idEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEmpresa(String value) {
        this.idEmpresa = value;
    }

    /**
     * Gets the value of the idPersona property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPersona() {
        return idPersona;
    }

    /**
     * Sets the value of the idPersona property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPersona(String value) {
        this.idPersona = value;
    }

    /**
     * Gets the value of the idTipoContenido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTipoContenido() {
        return idTipoContenido;
    }

    /**
     * Sets the value of the idTipoContenido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTipoContenido(String value) {
        this.idTipoContenido = value;
    }

    /**
     * Gets the value of the tipoArchivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoArchivo() {
        return tipoArchivo;
    }

    /**
     * Sets the value of the tipoArchivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoArchivo(String value) {
        this.tipoArchivo = value;
    }

}