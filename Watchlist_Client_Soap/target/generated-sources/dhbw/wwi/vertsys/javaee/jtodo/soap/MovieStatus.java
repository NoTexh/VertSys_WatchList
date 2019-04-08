
package dhbw.wwi.vertsys.javaee.jtodo.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für movieStatus.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="movieStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="GESEHEN"/&gt;
 *     &lt;enumeration value="NICHT_GESEHEN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "movieStatus")
@XmlEnum
public enum MovieStatus {

    GESEHEN,
    NICHT_GESEHEN;

    public String value() {
        return name();
    }

    public static MovieStatus fromValue(String v) {
        return valueOf(v);
    }

}
