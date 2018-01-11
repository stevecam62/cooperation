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
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Queries;
import javax.jdo.annotations.Query;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;

import au.org.cooperation.modules.base.dom.OrganisationContext;
import au.org.cooperation.modules.base.dom.impl.OrganisationPerson.OrganisationPersonStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
@XmlJavaTypeAdapter(org.apache.isis.schema.utils.jaxbadapters.PersistentEntityAdapter.class)
public class Organisation implements OrganisationContext{

	@Column(allowsNull = "false", length = 50)
	@Getter
	@Setter
	private String name;

	@Column(allowsNull = "true", length = 1000)
	@Getter
	@Setter
	private String description;

	@Persistent(mappedBy = "organisation", column = "aim_id")
	@Order(column = "org_aim_idx")
	@Getter
	private List<Aim> aims  = new ArrayList<>();

	@Persistent(mappedBy = "organisation", column = "goal_id")
	@Order(column = "org_goal_idx")
	@Getter
	private List<Goal> goals = new ArrayList<>();

	@Persistent(mappedBy = "organisation")
	@Getter(value = AccessLevel.PRIVATE)
	private List<OrganisationPerson> persons = new ArrayList<>();

	@Persistent(mappedBy = "organisation", column = "plan_id")
	@Order(column = "org_plan_idx")
	@Getter
	private List<Plan> plans = new ArrayList<>();

	public Organisation() {
	}

	public Organisation(String name) {
		setName(name);
	}
	
	//implements OrganisationContext
	@Override
	@Programmatic
	public Organisation getOrganisation() {
		return this;
	}
	
	@Override
	@Programmatic
	public String disables(OrganisationPerson orgPerson) {
		if (orgPerson == null) {
			return "No access allowed";
		} else {
			return (orgPerson.isAdministrator()) ? null : "User is not an administrator of this Organisation";
		}
	}

	@Override
	@Programmatic
	public String hides(OrganisationPerson orgPerson) {
		if (orgPerson == null) {
			return "No access allowed";
		} else {
			return  null;
		}
	}
	//end implements OrganisationContext

	public String title() {
		return getName();
	}
	
	public OrganisationAdmin showAdmin() {
		return new OrganisationAdmin(this);
	}
	
	public boolean hideShowAdmin() {
		Person person = personRepository.currentPerson();
		if(person.isSystemAdmin())
			return true;
		else
			return (person.getOrgPerson(null) != null) ? !person.getOrgPerson(null).isAdministrator() : true;
	}

	public Organisation addAim(@ParameterLayout(named = "Aim name") String name) {
		this.getAims().add(organisationRepository.createAim(this, name));
		return this;
	}

	public Organisation addGoal(@ParameterLayout(named = "Goal name") String name,
			@ParameterLayout(named = "Primary Aim") Aim aim) {
		this.getGoals().add(organisationRepository.createGoal(this, name, aim));
		return this;
	}

	public List<Aim> choices1AddGoal() {
		return this.getAims();
	}

	public String disableAddGoal() {
		if (this.getAims().size() == 0)
			return "A Goal must be linked to at least one Aim, so add an Aim first";
		else
			return null;
	}

	public Organisation addPlan(@ParameterLayout(named = "Plan name") String name) {
		this.getPlans().add(organisationRepository.createPlan(this, name));
		return this;
	}


	@Programmatic
	public OrganisationPerson addPerson(Person person, boolean isCreator, boolean isAdministrator) {
		OrganisationPerson orgPerson = organisationRepository.createOrganisationPerson(this, person,
				OrganisationPersonStatus.ACTIVE);
		orgPerson.setCreator(isCreator);
		orgPerson.setAdministrator(isAdministrator);
		this.getPersons().add(orgPerson);
		person.addOrganisation(orgPerson);
		return orgPerson;
	}

	@Programmatic
	public boolean isCreator(Person person) {
		for (OrganisationPerson orgPerson : this.getPersons()) {
			if (orgPerson.getPerson().equals(person)) {
				return orgPerson.isCreator();
			}
			if (orgPerson.isCreator()) {
				return false;
			}
		}
		return false;
	}

	@Programmatic
	public boolean isAdministrator(Person person) {
		for (OrganisationPerson orgPerson : this.getPersons()) {
			if (orgPerson.getPerson().equals(person)) {
				return orgPerson.isAdministrator();
			}
		}
		return false;
	}

	public Integer getActiveLinkedPersonCount() {
		return this.listActiveOrganisationPersons().size();
	}

	public OrganisationPersons showLinkedPersons() {
		return new OrganisationPersons(this);
	}

	@Programmatic
	public boolean hasLinkedPerson(Person person) {
		boolean found = false;
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getPerson().equals(person)) {
				found = true;
				break;
			}
		}
		return found;
	}

	@Programmatic
	public List<Person> listActivePersons() {
		List<Person> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.ACTIVE)) {
				temp.add(op.getPerson());
			}
		}
		return temp;
	}

	@Programmatic
	public List<OrganisationPersonView> listActivePersonViews() {
		List<OrganisationPersonView> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.ACTIVE)) {
				temp.add(new OrganisationPersonView(op));
			}
		}
		return temp;
	}

	@Programmatic
	List<OrganisationPerson> listActiveOrganisationPersons() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.ACTIVE)) {
				temp.add(op);
			}
		}
		return temp;
	}

	@Programmatic
	public List<Person> listInactivePersons() {
		List<Person> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.INACTIVE)) {
				temp.add(op.getPerson());
			}
		}
		return temp;
	}

	@Programmatic
	public List<OrganisationPersonView> listInactivePersonViews() {
		List<OrganisationPersonView> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.INACTIVE)) {
				temp.add(new OrganisationPersonView(op));
			}
		}
		return temp;
	}

	@Programmatic
	public OrganisationPersonStatus linkedPersonStatus(Person person) {
		List<Person> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getPerson().equals(person)) {
				return op.getStatus();
			}
		}
		return null;
	}

	@Programmatic
	public List<OrganisationPerson> listInactiveOrganisationPersons() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getPersons()) {
			if (op.getStatus().equals(OrganisationPersonStatus.INACTIVE)) {
				temp.add(op);
			}
		}
		return temp;
	}

	@Programmatic
	public List<Person> listAdministrators() {
		ArrayList<Person> admins = new ArrayList<>();
		for (OrganisationPerson op : this.listActiveOrganisationPersons()) {
			if (op.isAdministrator()) {
				admins.add(op.getPerson());
			}
		}
		return admins;
	}

	@Inject
	OrganisationRepository organisationRepository;

	@Inject
	PersonRepository personRepository;




}
