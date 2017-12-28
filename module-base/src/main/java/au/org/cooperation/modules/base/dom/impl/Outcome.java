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
 */package au.org.cooperation.modules.base.dom.impl;

import java.util.List;

import javax.jdo.annotations.*;

import org.apache.isis.applib.annotation.DomainObject;

import au.org.cooperation.modules.base.dom.AbstractOrganisationContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@DomainObject()
public class Outcome extends AbstractOrganisationContext {

	@Column(allowsNull = "true", length = 5000)
	@Getter
	@Setter
	protected String description;

	@Column(allowsNull = "true", name="goal_id")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	protected Goal goal;

	@Column(allowsNull = "true", name="task_id")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private Task task;

	// m-n relationship
	@Join
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	protected List<Result> results;

	Outcome() {
	}

	public Outcome(String description) {
		setDescription(description);
	}

	public Outcome(Organisation organisation, Goal goal, String description) {
		setOrganisation(organisation);
		setGoal(goal);
		setDescription(description);
	}

	public String title() {
		return (getDescription() != null)
				? ((getDescription().length() > 49) ? getDescription().substring(0, 50) : getDescription()) : null;
	}
}
