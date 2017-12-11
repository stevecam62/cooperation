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
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;

import au.org.cooperation.modules.base.dom.impl.OrganisationPerson.OrganisationPersonStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
// @Discriminator(column="level", value="TASK")
@DomainObject()
public class Task {

	@Column(allowsNull = "false", name = "organisation_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	private Organisation organisation;

	@Column(allowsNull = "true", name = "parent_task_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	private Task parentTask;

	@Column(allowsNull = "false", length = 50)
	@Getter
	@Setter
	protected String name;

	@Column(allowsNull = "true", length = 2000)
	@Getter
	@Setter
	private String description;

	@Column(allowsNull = "true", name = "goal_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	public Goal goal;

	@Persistent
	@Join
	@Getter(value = AccessLevel.PACKAGE)
	protected List<Person> persons;

	@Persistent(mappedBy = "task")
	@Order(column = "task_effort_idx")
	@Getter
	protected List<Effort> efforts;

	@Persistent(mappedBy = "task")
	@Order(column = "task_result_idx")
	@Getter
	protected List<Result> results;

	@Persistent(mappedBy = "task")
	@Order(column = "task_outcome_idx")
	@Getter
	protected List<Outcome> outcomes;

	@Persistent(mappedBy = "parentTask")
	@Order(column = "task_subtask_idx")
	@Getter
	protected List<Task> subTasks;

	Task() {
	}

	public Task(Organisation organisation, String name) {
		this.setName(name);
	}

	public Task(Organisation organisation, Goal goal, String name) {
		this.setOrganisation(organisation);
		this.setGoal(goal);
		this.setName(name);
	}

	public Task(Task task, String name) {
		this.setOrganisation(task.getOrganisation());
		this.setGoal(task.getGoal());
		this.setParentTask(task);
		this.setName(name);
	}

	public String title() {
		return getName();
	}

	public Task addPerson(@ParameterLayout(named = "Person") Person person) {
		this.getPersons().add(person);
		person.getTasks().add(this);
		return this;
	}

	public List<Person> choices0AddPerson() {
		List<Person> temp1 = null;
		if (this.getGoal() != null && this.getGoal().getOrganisation() != null) {
			temp1 = this.getGoal().getOrganisation().listActivePersons();
		} else {
			temp1 = personRepository.listAll();
		}
		// check if already linked
		List<Person> temp2 = new ArrayList<>();
		for (Person person : temp1) {
			if (!this.getPersons().contains(person)) {
				temp2.add(person);
			}
		}
		return temp2;
	}

	public Task removePerson(@ParameterLayout(named = "Person") Person person) {
		this.getPersons().remove(person);
		return this;
	}

	public List<Person> choices0RemovePerson() {
		return this.getPersons();
	}

	@NotPersistent
	public List<PersonView> getPersonViews() {
		List<PersonView> temp = new ArrayList<>();
		for (Person person : this.getPersons()) {
			// person should be linked to organisation but...
			OrganisationPersonStatus status = this.getOrganisation().linkedPersonStatus(person);
			if (status != null && status.equals(OrganisationPersonStatus.ACTIVE))
				temp.add(new PersonView(person));
		}
		return temp;
	}

	public Task addEffort(@ParameterLayout(named = "Person") Person person, DateTime start, DateTime end) {
		Effort effort = taskRepository.createEffort(this, person, start, end);
		this.getEfforts().add(effort);
		person.getEfforts().add(effort);
		return this;
	}

	public List<Person> choices0AddEffort() {
		return this.getPersons();
	}

	public Task addResult(@ParameterLayout(named = "Result description") String description) {
		this.getResults().add(taskRepository.createResult(this, description));
		return this;
	}

	public Task addOutcome(@ParameterLayout(named = "Outcome description") String description,
			@ParameterLayout(named = "First result") Result result) {
		Outcome outcome = organisationRepository.createOutcome(this, description);
		this.getOutcomes().add(outcome);
		if (this.getGoal() != null) {
			this.getGoal().getOutcomes().add(outcome);
		}
		if (result != null) {
			outcome.getResults().add(result);
			result.getOutcomes().add(outcome);
		}
		return this;
	}

	public List<Result> choices1AddOutcome() {
		// TODO has result been added?
		return this.getResults();
	}

	public Task addSubTask(@ParameterLayout(named = "Task name") String name) {
		this.getSubTasks().add(taskRepository.createSubTask(this, name));
		return this;
	}

	@Inject
	TaskRepository taskRepository;

	@Inject
	OrganisationRepository organisationRepository;

	@Inject
	PersonRepository personRepository;
}
