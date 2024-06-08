package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java-Klasse für groupNameType.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * <pre>{@code
 * <simpleType name="groupNameType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="basejava01"/>
 *     <enumeration value="basejava02"/>
 *     <enumeration value="basejava03"/>
 *     <enumeration value="topjava01"/>
 *     <enumeration value="topjava02"/>
 *     <enumeration value="topjava03"/>
 *     <enumeration value="masterjava01"/>
 *     <enumeration value="masterjava02"/>
 *     <enumeration value="masterjava03"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "groupNameType", namespace = "http://javaops.ru")
@XmlEnum
public enum GroupNameType {

    @XmlEnumValue("basejava01")
    BASEJAVA_01("basejava01"),
    @XmlEnumValue("basejava02")
    BASEJAVA_02("basejava02"),
    @XmlEnumValue("basejava03")
    BASEJAVA_03("basejava03"),
    @XmlEnumValue("topjava01")
    TOPJAVA_01("topjava01"),
    @XmlEnumValue("topjava02")
    TOPJAVA_02("topjava02"),
    @XmlEnumValue("topjava03")
    TOPJAVA_03("topjava03"),
    @XmlEnumValue("masterjava01")
    MASTERJAVA_01("masterjava01"),
    @XmlEnumValue("masterjava02")
    MASTERJAVA_02("masterjava02"),
    @XmlEnumValue("masterjava03")
    MASTERJAVA_03("masterjava03");
    private final String value;

    GroupNameType(String v) {
        value = v;
    }

    /**
     * Gets the value associated to the enum constant.
     * 
     * @return
     *     The value linked to the enum.
     */
    public String value() {
        return value;
    }

    /**
     * Gets the enum associated to the value passed as parameter.
     * 
     * @param v
     *     The value to get the enum from.
     * @return
     *     The enum which corresponds to the value, if it exists.
     * @throws IllegalArgumentException
     *     If no value matches in the enum declaration.
     */
    public static GroupNameType fromValue(String v) {
        for (GroupNameType c: GroupNameType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
