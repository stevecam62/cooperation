package au.org.cooperation.modules.base.dom.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "Organisation", propOrder = { "name", "description", "aims", "plans", "goals" })
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class OrganisationPerson {

	// @XmlElement(required = true)
	@Column(allowsNull = "false", name="organisation_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	protected Organisation organisation;

	// @XmlElement
	@Column(allowsNull = "false", name="person_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	protected Person person;

	@Column(allowsNull = "false")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	protected OrganisationPersonStatus status;
	
	OrganisationPerson(){
		
	}
	
	public OrganisationPerson(Organisation organisation, Person person, OrganisationPersonStatus status){
		setOrganisation(organisation);
		setPerson(person);
		setStatus(status);
	}
	
	public String title(){
		return this.getPerson().title();
	}

	public enum OrganisationPersonStatus {
		ACTIVE, INACTIVE;

		public String title() {
			return this.name();
		}
	}

}