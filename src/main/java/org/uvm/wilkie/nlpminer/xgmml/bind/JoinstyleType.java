//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.09 at 06:45:19 PM EST 
//


package org.uvm.wilkie.nlpminer.xgmml.bind;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for joinstyle.type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="joinstyle.type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="bevel"/>
 *     &lt;enumeration value="miter"/>
 *     &lt;enumeration value="round"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "joinstyle.type")
@XmlEnum
public enum JoinstyleType {

    @XmlEnumValue("bevel")
    BEVEL("bevel"),
    @XmlEnumValue("miter")
    MITER("miter"),
    @XmlEnumValue("round")
    ROUND("round");
    private final String value;

    JoinstyleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static JoinstyleType fromValue(String v) {
        for (JoinstyleType c: JoinstyleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}