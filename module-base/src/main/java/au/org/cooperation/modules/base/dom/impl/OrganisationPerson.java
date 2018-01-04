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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlType;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.jdo.annotations.Unique;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Programmatic;

import au.org.cooperation.modules.base.dom.OrganisationContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation", table = "organisation_person")
@Queries({
		@Query(name = "find", value = "SELECT FROM au.org.cooperation.modules.base.dom.impl.OrganisationPerson "
				+ "WHERE organisation == :organisation && person == :person "),
		@Query(name = "findLinkedToPerson", value = "SELECT FROM au.org.cooperation.modules.base.dom.impl.OrganisationPerson "
				+ "WHERE person == :person ") })
@Unique(name = "OrganisationPerson_org_pers_UNQ", members = { "organisation", "person" })
@DomainObject()
public class OrganisationPerson implements OrganisationContext {

	@Column(allowsNull = "false", name = "organisation_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	protected Organisation organisation;

	@Column(allowsNull = "false", name = "person_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	protected Person person;

	@Column(allowsNull = "false")
	@Getter
	@Setter
	protected boolean isAdministrator;

	@Column(allowsNull = "false")
	@Getter
	@Setter
	protected boolean isCreator;

	@Column(allowsNull = "false")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	protected OrganisationPersonStatus status;

	OrganisationPerson() {

	}

	public OrganisationPerson(Organisation organisation, Person person, OrganisationPersonStatus status) {
		setOrganisation(organisation);
		setPerson(person);
		setStatus(status);
		setAdministrator(false);
		setCreator(false);
	}

	public String title() {
		return this.getPerson().title();
	}

	public enum OrganisationPersonStatus {
		ACTIVE, INACTIVE;

		public String title() {
			return this.name();
		}
	}

	@Override
	@Programmatic
	public String disables(OrganisationPerson orgPerson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Programmatic
	public String hides(OrganisationPerson orgPerson) {
		// TODO Auto-generated method stub
		return null;
	}

}