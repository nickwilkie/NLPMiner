//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.09 at 06:45:19 PM EST 
//


package org.uvm.wilkie.nlpminer.xgmml.bind;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for graphicEdge complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="graphicEdge">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.cs.rpi.edu/XGMML}simpleEdge">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.cs.rpi.edu/XGMML}graphics" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "graphicEdge", propOrder = {
    "graphics"
})
public class GraphicEdge
    extends SimpleEdge
{

    protected Graphics graphics;

    /**
     * Gets the value of the graphics property.
     * 
     * @return
     *     possible object is
     *     {@link Graphics }
     *     
     */
    public Graphics getGraphics() {
        return graphics;
    }

    /**
     * Sets the value of the graphics property.
     * 
     * @param value
     *     allowed object is
     *     {@link Graphics }
     *     
     */
    public void setGraphics(Graphics value) {
        this.graphics = value;
    }

}
