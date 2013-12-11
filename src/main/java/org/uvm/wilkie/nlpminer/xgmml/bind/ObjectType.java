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
 * <p>Java class for object.type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="object.type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *     &lt;enumeration value="list"/>
 *     &lt;enumeration value="string"/>
 *     &lt;enumeration value="real"/>
 *     &lt;enumeration value="integer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "object.type")
@XmlEnum
public enum ObjectType {

    @XmlEnumValue("list")
    LIST("list"),
    @XmlEnumValue("string")
    STRING("string"),
    @XmlEnumValue("real")
    REAL("real"),
    @XmlEnumValue("integer")
    INTEGER("integer");
    private final String value;

    ObjectType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectType fromValue(String v) {
        for (ObjectType c: ObjectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}