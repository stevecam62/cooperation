package au.org.cooperation.modules.base.dom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import au.org.cooperation.modules.base.dom.impl.OrganisationPerson.OrganisationPersonStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "OrganisationPersons")
@XmlType(propOrder = { "organisation" })
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganisationPersons {

	@XmlElement(required = true)
	@Getter(value=AccessLevel.PRIVATE)
	@Setter()
	private Organisation organisation;

	public OrganisationPersons() {
	}

	public OrganisationPersons(Organisation organisation) {
		this.setOrganisation(organisation);
	}
	
	public String title(){
		return "People linked to: " + this.getOrganisation().title();
	}

	@XmlTransient
	public List<Person> getActivePersons() {
		return this.getOrganisation().listActivePersons();
	}

	@XmlTransient
	public List<Person> getInactivePersons() {
		return this.getOrganisation().listInactivePersons();
	}

	public OrganisationPersons addPerson(Person person) {
		this.getOrganisation().addPerson(person);
		return this;
	}

	public List<Person> choices0AddPerson() {
		List<Person> persons = new ArrayList<>();
		for (Person person : personRepository.listAll()) {
			if (!this.getOrganisation().hasLinkedPerson(person)) {
				persons.add(person);
			}
		}
		return persons;
	}

	public OrganisationPersons inactivatePerson(OrganisationPerson person) {
		person.setStatus(OrganisationPersonStatus.INACTIVE);
		return this;
	}

	public List<OrganisationPerson> choices0InactivatePerson() {
		return this.getOrganisation().listActiveOrganisationPersons();
	}

	public OrganisationPersons activatePerson(OrganisationPerson person) {
		person.setStatus(OrganisationPersonStatus.ACTIVE);
		return this;
	}

	public List<OrganisationPerson> choices0ActivatePerson() {
		return this.getOrganisation().listInactiveOrganisationPersons();
	}

	@XmlTransient
	@Inject
	PersonRepository personRepository;

}
