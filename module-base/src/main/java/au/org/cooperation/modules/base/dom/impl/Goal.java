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
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Goal {

	@Column(allowsNull = "false", name = "organisation_id")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	protected Organisation organisation;

	@Column(allowsNull = "false", length = 100)
	@Getter
	@Setter
	protected String name;

	@Column(allowsNull = "true", length = 1000)
	@Getter
	@Setter
	protected String description;

	@Column(allowsNull = "false", name = "aim_id")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	protected Aim aim;

	@Column(allowsNull = "true", name = "plan_id")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	protected Plan plan;

	@Persistent(mappedBy = "goal")
	@Order(column = "goal_task_idx")
	@Getter
	protected List<Task> tasks;

	@Persistent(mappedBy = "goal")
	@Order(column = "goal_outcome_idx")
	@Getter
	protected List<Outcome> outcomes;

	Goal() {
	}

	public Goal(Organisation organisation, String name, Aim aim) {
		setOrganisation(organisation);
		setName(name);
		setAim(aim);
	}

	public String title() {
		return getName();
	}

	public Goal addTask(@ParameterLayout(named = "Task name") String name) {
		this.getTasks().add(taskRepository.createTask(this, name));
		return this;
	}
	
	@Programmatic
	public Task addTask(String name, String description) {
		Task task = taskRepository.createTask(this, name);
		task.setDescription(description);
		this.getTasks().add(task);
		return task;
	}

	@Inject
	OrganisationRepository organisationRepository;

	@Inject
	TaskRepository taskRepository;



}
