package ru.javaops.masterjava.xml.schema;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       <all>
 *         <element name="Users">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence maxOccurs="unbounded" minOccurs="0">
 *                   <element ref="{http://javaops.ru}User"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="Cities">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence maxOccurs="unbounded">
 *                   <element ref="{http://javaops.ru}City"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="Projects">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence maxOccurs="unbounded">
 *                   <element ref="{http://javaops.ru}Project"/>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *       </all>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Payload", namespace = "http://javaops.ru")
public class Payload {

    @XmlElement(name = "Users", namespace = "http://javaops.ru", required = true)
    protected Payload.Users users;
    @XmlElement(name = "Cities", namespace = "http://javaops.ru", required = true)
    protected Payload.Cities cities;
    @XmlElement(name = "Projects", namespace = "http://javaops.ru", required = true)
    protected Payload.Projects projects;

    /**
     * Ruft den Wert der users-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Payload.Users }
     *     
     */
    public Payload.Users getUsers() {
        return users;
    }

    /**
     * Legt den Wert der users-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload.Users }
     *     
     */
    public void setUsers(Payload.Users value) {
        this.users = value;
    }

    /**
     * Ruft den Wert der cities-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Payload.Cities }
     *     
     */
    public Payload.Cities getCities() {
        return cities;
    }

    /**
     * Legt den Wert der cities-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload.Cities }
     *     
     */
    public void setCities(Payload.Cities value) {
        this.cities = value;
    }

    /**
     * Ruft den Wert der projects-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Payload.Projects }
     *     
     */
    public Payload.Projects getProjects() {
        return projects;
    }

    /**
     * Legt den Wert der projects-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Payload.Projects }
     *     
     */
    public void setProjects(Payload.Projects value) {
        this.projects = value;
    }


    /**
     * <p>Java-Klasse für anonymous complex type.</p>
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence maxOccurs="unbounded">
     *         <element ref="{http://javaops.ru}City"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "city"
    })
    public static class Cities {

        @XmlElement(name = "City", required = true)
        protected List<CityType> city;

        /**
         * Gets the value of the city property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the city property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getCity().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CityType }
         * </p>
         * 
         * 
         * @return
         *     The value of the city property.
         */
        public List<CityType> getCity() {
            if (city == null) {
                city = new ArrayList<>();
            }
            return this.city;
        }

    }


    /**
     * <p>Java-Klasse für anonymous complex type.</p>
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence maxOccurs="unbounded">
     *         <element ref="{http://javaops.ru}Project"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "project"
    })
    public static class Projects {

        @XmlElement(name = "Project", required = true)
        protected List<Project> project;

        /**
         * Gets the value of the project property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the project property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getProject().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Project }
         * </p>
         * 
         * 
         * @return
         *     The value of the project property.
         */
        public List<Project> getProject() {
            if (project == null) {
                project = new ArrayList<>();
            }
            return this.project;
        }

    }


    /**
     * <p>Java-Klasse für anonymous complex type.</p>
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence maxOccurs="unbounded" minOccurs="0">
     *         <element ref="{http://javaops.ru}User"/>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "user"
    })
    public static class Users {

        @XmlElement(name = "User")
        protected List<User> user;

        /**
         * Gets the value of the user property.
         * 
         * <p>This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the user property.</p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         * getUser().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link User }
         * </p>
         * 
         * 
         * @return
         *     The value of the user property.
         */
        public List<User> getUser() {
            if (user == null) {
                user = new ArrayList<>();
            }
            return this.user;
        }

    }

}
