//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.18 at 03:56:26 PM AEST 
//


package domainapp.modules.base.dom.impl.generated;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (au.org.cooperation.modules.base.dom.impl.DataTypeAdapter.parseDateTime(value));
    }

    public String marshal(Date value) {
        return (au.org.cooperation.modules.base.dom.impl.DataTypeAdapter.printDateTime(value));
    }

}
