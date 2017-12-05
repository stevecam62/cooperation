package au.org.cooperation.modules.base.dom.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.*;
import javax.xml.bind.annotation.*;
import org.apache.isis.applib.annotation.*;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import lombok.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
	"familyName",
	"givenName",
	"dateOfBirth",
    "efforts",
    "rewards"
})

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@DomainObject()
public class Person extends ApplicationUser {
	
	/*
	 * Allow a default Organisation to be set on the current user.
	 * 
	 * Organisations have a list of linked users/Persons, but one user might link to
	 * multiple Organisations, but we want to restrict the visibility to one Organisation at a time.
	 * 
	 * We restrict access to all data usinf the security module tenancy path, but this requires
	 * a current Organisation, that is set here a login, by the user having only one link to an 
	 * Organisation, or, the user selecting one specifically. In the later case the currently operating
	 * one will be saved from session to session.
	 * 
	 */
    @XmlTransient
    @Column(allowsNull="true", name="organisation_id")
    @Getter 
    @Setter(value=AccessLevel.PACKAGE)
	private Organisation organisation;

    @XmlElement(name = "family-name", required = true)
    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private String familyName;
    
    @XmlElement(name = "given-name", required = true)
    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private String givenName;
    
    @XmlElement(name = "date-of-birth", required = true)
    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private java.sql.Date dateOfBirth;

    //@XmlElement
    //@Column(allowsNull="true")
    //@Getter 
    //@Setter
    //private Algorithm algorithm;
    
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
    
    public String title(){
    	return this.getGivenName() + " " + getFamilyName();
    }

}
