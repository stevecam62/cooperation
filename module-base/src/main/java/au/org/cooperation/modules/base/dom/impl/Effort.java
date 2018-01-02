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

import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.joda.time.DateTime;

import au.org.cooperation.modules.base.dom.StartAndFinishDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Effort extends StartAndFinishDateTime {
    
	@Column(allowsNull = "true", name="reward_id")
	@Getter(value=AccessLevel.PACKAGE)
	@Setter(value=AccessLevel.PACKAGE)
	private Reward reward;

	@Column(allowsNull = "true", name="result_id")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private Result result;

	@Column(allowsNull = "false", name="person_id")
	@Getter(value = AccessLevel.PROTECTED)
	@Setter(value = AccessLevel.PROTECTED)
	private Person person;

	@Column(allowsNull = "true", name="task_id")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private Task task;

	Effort() {
	}

	public Effort(Organisation organisation, Task task, Person person, DateTime start, DateTime end) {
		setOrganisation(organisation);
		setPerson(person);
		setTask(task);
		setStartDateTime(start);
		setEndDateTime(end);
	}
	
	public String title(){
		return "Effort by " + this.getPerson().title();
	}
	
	@NotPersistent
	public PersonView getPersonView(){
		return new PersonView(this.getPerson());
	}

	public Effort assignToResult(Result result) {
		this.setResult(result);
		this.getTask().getResults().add(result);
		return this;
	}
	
	public String disableAssignToResult(){
		if(this.getTask() == null || this.getTask().getResults().size() == 0)
			return "No parent Task or Results associated with the parent Task";
		else
			return null;
	}
	
	public List<Result> choices0AssignToResult(){
		if(this.getTask() != null && this.getTask().getResults().size() > 0)
			return this.getTask().getResults();
		else
			return null;		
	}

	public Effort addResult(@ParameterLayout(named = "Result description") String description) {
		Result result = taskRepository.createResult(this.getTask(), description);
		this.setResult(result);
		this.getTask().getResults().add(result);
		return this;
	}
	
	@Override
	@Programmatic
	public String disables(OrganisationPerson orgPerson) {
		if (orgPerson == null) {
			return "No access";
		} else {
			return null;
		}
	}
	
	@Inject
	TaskRepository taskRepository;

}
