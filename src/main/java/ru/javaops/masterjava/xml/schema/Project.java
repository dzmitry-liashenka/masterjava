package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *       <attribute name="status" use="required" type="{http://javaops.ru}groupStatusType" />
 *       <attribute name="group" use="required" type="{http://javaops.ru}groupNameType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "description"
})
@XmlRootElement(name = "Project")
public class Project {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    @XmlAttribute(name = "status", required = true)
    protected GroupStatusType status;
    @XmlAttribute(name = "group", required = true)
    protected GroupNameType group;

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der description-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Legt den Wert der description-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Ruft den Wert der status-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link GroupStatusType }
     *     
     */
    public GroupStatusType getStatus() {
        return status;
    }

    /**
     * Legt den Wert der status-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupStatusType }
     *     
     */
    public void setStatus(GroupStatusType value) {
        this.status = value;
    }

    /**
     * Ruft den Wert der group-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link GroupNameType }
     *     
     */
    public GroupNameType getGroup() {
        return group;
    }

    /**
     * Legt den Wert der group-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupNameType }
     *     
     */
    public void setGroup(GroupNameType value) {
        this.group = value;
    }

}
