package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java-Klasse für groupType complex type.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * 
 * <pre>{@code
 * <complexType name="groupType">
 *   <simpleContent>
 *     <extension base="<http://www.w3.org/2001/XMLSchema>string">
 *       <attribute name="grp" use="required" type="{http://javaops.ru}groupNameType" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupType", namespace = "http://javaops.ru", propOrder = {
    "value"
})
public class GroupType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "grp", required = true)
    protected GroupNameType grp;

    /**
     * Ruft den Wert der value-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Legt den Wert der value-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Ruft den Wert der grp-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link GroupNameType }
     *     
     */
    public GroupNameType getGrp() {
        return grp;
    }

    /**
     * Legt den Wert der grp-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupNameType }
     *     
     */
    public void setGrp(GroupNameType value) {
        this.grp = value;
    }

}
