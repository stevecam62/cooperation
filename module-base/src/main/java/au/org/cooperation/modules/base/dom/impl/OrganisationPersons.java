/*
 *
 *  Copyright 2017 Alexander Stephen Cameron
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
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

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.SemanticsOf;

import au.org.cooperation.modules.base.dom.OrganisationContext;
import au.org.cooperation.modules.base.dom.impl.OrganisationPerson.OrganisationPersonStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "OrganisationPersons")
@XmlType(propOrder = { "organisation" })
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganisationPersons implements OrganisationContext{

	@XmlElement(required = true)
	@Getter()
	@Setter(value=AccessLevel.PACKAGE)
	private Organisation organisation;

	public OrganisationPersons() {
	}

	public OrganisationPersons(Organisation organisation) {
		this.setOrganisation(organisation);
	}

	public String title() {
		return "People linked to: " + this.getOrganisation().title();
	}

	@XmlTransient
	public List<OrganisationPersonView> getActivePersons() {
		return this.getOrganisation().listActivePersonViews();
	}

	@XmlTransient
	public List<OrganisationPersonView> getInactivePersons() {
		return this.getOrganisation().listInactivePersonViews();
	}
	
	public boolean hideInactivePersons() {
		if (!this.getOrganisation().isAdministrator(personRepository.currentPerson())) {
			return true;
		} else {
			return false;
		}
	}

	public OrganisationPersons addPerson(Person person) {
		this.getOrganisation().addPerson(person, false, false);
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

	public boolean hideAddPerson() {
		if (!this.getOrganisation().isAdministrator(personRepository.currentPerson())) {
			return true;
		} else {
			return false;
		}
	}

	@Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
	public OrganisationPersons inactivatePerson(OrganisationPerson orgPerson) {
		orgPerson.setStatus(OrganisationPersonStatus.INACTIVE);
		if(orgPerson.getPerson().getOrgPerson().equals(orgPerson)){
			orgPerson.getPerson().setOrgPerson(null);
		}
		return this;
	}

	public List<OrganisationPerson> choices0InactivatePerson() {
		return this.getOrganisation().listActiveOrganisationPersons();
	}

	public boolean hideInactivatePerson() {
		if (!this.getOrganisation().isAdministrator(personRepository.currentPerson())) {
			return true;
		} else {
			return false;
		}
	}

	public OrganisationPersons activatePerson(OrganisationPerson person) {
		person.setStatus(OrganisationPersonStatus.ACTIVE);
		return this;
	}

	public List<OrganisationPerson> choices0ActivatePerson() {
		return this.getOrganisation().listInactiveOrganisationPersons();
	}

	public boolean hideActivatePerson() {
		if (!this.getOrganisation().isAdministrator(personRepository.currentPerson())) {
			return true;
		} else {
			return false;
		}
	}

	@XmlTransient
	@Inject
	PersonRepository personRepository;

	@XmlTransient
	@Inject
	OrganisationRepository organisationRepository;

}
