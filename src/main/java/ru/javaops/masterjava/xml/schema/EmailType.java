package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java-Klasse für emailType.</p>
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.</p>
 * <pre>{@code
 * <simpleType name="emailType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="gmail@gmail.com"/>
 *     <enumeration value="admin@javaops.ru"/>
 *     <enumeration value="mail@yandex.ru"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "emailType")
@XmlEnum
public enum EmailType {

    @XmlEnumValue("gmail@gmail.com")
    GMAIL_GMAIL_COM("gmail@gmail.com"),
    @XmlEnumValue("admin@javaops.ru")
    ADMIN_JAVAOPS_RU("admin@javaops.ru"),
    @XmlEnumValue("mail@yandex.ru")
    MAIL_YANDEX_RU("mail@yandex.ru");
    private final String value;

    EmailType(String v) {
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
    public static EmailType fromValue(String v) {
        for (EmailType c: EmailType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
