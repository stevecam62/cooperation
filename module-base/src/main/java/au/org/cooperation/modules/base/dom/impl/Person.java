package au.org.cooperation.modules.base.dom.impl;

import java.util.List;

import javax.jdo.annotations.*;
import org.apache.isis.applib.annotation.*;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import lombok.*;

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
	 * We restrict access to all data, but this requires
	 * a current Organisation, that is set here at login, by the user having only one link to an 
	 * Organisation, or, the user selecting one specifically. In the later case the currently operating
	 * one will be saved from session to session.
	 * 
	 */
    @Column(allowsNull="true", name="organisation_id")
    @Getter 
    @Setter(value=AccessLevel.PACKAGE)
	private Organisation organisation;

    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private String familyName;
    
    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private String givenName;
    
    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private java.sql.Date dateOfBirth;

    //@XmlElement
    //@Column(allowsNull="true")
    //@Getter 
    //@Setter
    //private Algorithm algorithm;
    
	@Persistent
	@Join
	@Getter
	private List<Task> tasks;
	
	@Persistent(mappedBy="person")
	@Getter
	private List<Effort> efforts;
	
	@Persistent(mappedBy="person")
	@Getter
	private List<Reward> rewards;
    
    public String title(){
    	return this.getGivenName() + " " + getFamilyName();
    }

}
