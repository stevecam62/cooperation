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

import javax.jdo.annotations.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.*;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import au.org.cooperation.modules.base.dom.impl.OrganisationPerson.OrganisationPersonStatus;
import lombok.*;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@DomainObject()
@XmlJavaTypeAdapter(org.apache.isis.schema.utils.jaxbadapters.PersistentEntityAdapter.class)
public class Person extends ApplicationUser {

	@Column(allowsNull = "true") // really false but security module adds 1 user
	@Getter
	@Setter
	private String familyName;

	@Column(allowsNull = "true") // really false but security module adds 1 user
	@Getter
	@Setter
	private String givenName;

	@Column(allowsNull = "true") // really false but security module adds 1 user
	@Getter
	@Setter
	private java.sql.Date dateOfBirth;

	// @XmlElement
	// @Column(allowsNull="true")
	// @Getter
	// @Setter
	// private Algorithm algorithm;

	@Persistent
	@Join
	@Getter
	private List<Task> tasks;

	@Persistent(mappedBy = "person")
	@Getter
	private List<Effort> efforts;

	@Persistent(mappedBy = "person")
	@Getter
	private List<Reward> rewards;

	@Persistent(mappedBy = "person")
	@Getter(value = AccessLevel.PRIVATE)
	private List<OrganisationPerson> organisations;

	/*
	 * Allow a default Organisation to be set on the current user.
	 */
	@Column(allowsNull = "true", name = "org_person_id")
	@Getter()
	@Setter(value = AccessLevel.PACKAGE)
	private OrganisationPerson orgPerson;

	public String title() {
		return this.getGivenName() + " " + getFamilyName();
	}

	@Programmatic
	public Organisation getCurrentOrganisation() {
		if (this.getOrgPerson() != null) { //setting to inactive can also set current to null
			return this.getOrgPerson().getOrganisation();
		} else if (this.getOrganisations().size() > 0) {
			//return first active found
			for(OrganisationPerson op : this.getOrganisations()){
				if(op.getStatus().equals(OrganisationPersonStatus.ACTIVE)){
					this.setOrgPerson(op);
					return op.getOrganisation();
				}
			}
		}
		return null;
	}

	@Programmatic
	void addOrganisation(OrganisationPerson orgPerson) {
		this.getOrganisations().add(orgPerson);
		this.setOrgPerson(orgPerson);
	}

	@Programmatic
	public List<Organisation> getLinkedOrganisations(boolean includeInactive, boolean includeCurrent) {
		List<Organisation> list = new ArrayList<>();
		for (OrganisationPerson op : this.getOrganisations()) {
			if (includeInactive || op.getStatus().equals(OrganisationPersonStatus.ACTIVE)) {
				if (includeCurrent || !this.getOrgPerson().equals(op))
					list.add(op.getOrganisation());
			}
		}
		return list;
	}

}
