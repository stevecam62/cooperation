package au.org.cooperation.modules.base.dom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.Getter;
import lombok.Setter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
	"familyName",
	"givenName",
	"dateOfBirth",
    "efforts",
    "rewards",
    "algorithm"
})

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Person {

    @XmlElement(name = "family-name", required = true)
    @Column(allowsNull="false")
    @Getter 
    @Setter
    private String familyName;
    
    @XmlElement(name = "given-name", required = true)
    @Column(allowsNull="false")
    @Getter 
    @Setter
    private String givenName;
    
    @XmlElement(name = "date-of-birth", required = true)
    @Column(allowsNull="false")
    @Getter 
    @Setter
    private Date dateOfBirth;

    @XmlElement
    @Column(allowsNull="true")
    @Getter 
    @Setter
    private Algorithm algorithm;
    
    @XmlTransient
	@Persistent
	@Join
	@Getter
	private List<Task> tasks;
	
    @XmlElement(name = "effort")
	@Persistent(mappedBy="person")
	@Getter
	private List<Effort> efforts;
	
    @XmlElement(name = "reward")
	@Persistent(mappedBy="person")
	@Getter
	private List<Reward> rewards;

}
