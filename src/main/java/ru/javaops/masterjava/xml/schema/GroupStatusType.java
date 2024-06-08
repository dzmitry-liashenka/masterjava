package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java-Klasse für groupStatusType.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * <pre>{@code
 * <simpleType name="groupStatusType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="REGISTERING"/>
 *     <enumeration value="CURRENT"/>
 *     <enumeration value="FINISHED"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "groupStatusType", namespace = "http://javaops.ru")
@XmlEnum
public enum GroupStatusType {

    REGISTERING,
    CURRENT,
    FINISHED;

    public String value() {
        return name();
    }

    public static GroupStatusType fromValue(String v) {
        return valueOf(v);
    }

}
