//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.15 at 02:17:03 PM EDT 
//


package net.tce.admin.adapter.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensajeDtoArray" type="{http://jaxws.adapter.admin.tce.net/}mensajeDtoArray"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mensajeDtoArray"
})
@XmlRootElement(name = "storeDocumentResponse")
public class StoreDocumentResponse {

    @XmlElement(required = true)
    protected MensajeDtoArray mensajeDtoArray;

    /**
     * Gets the value of the mensajeDtoArray property.
     * 
     * @return
     *     possible object is
     *     {@link MensajeDtoArray }
     *     
     */
    public MensajeDtoArray getMensajeDtoArray() {
        return mensajeDtoArray;
    }

    /**
     * Sets the value of the mensajeDtoArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link MensajeDtoArray }
     *     
     */
    public void setMensajeDtoArray(MensajeDtoArray value) {
        this.mensajeDtoArray = value;
    }

}