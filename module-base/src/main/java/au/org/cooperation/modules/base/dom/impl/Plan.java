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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;

import au.org.cooperation.modules.base.dom.AbstractOrganisationContext;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@DomainObject()
public class Plan extends AbstractOrganisationContext{

	@Column(allowsNull = "false", length=50)
	@Getter
	@Setter
	protected String name;
	
	@Column(allowsNull = "true", length=1000)
	@Getter
	@Setter
	protected String description;

	@Persistent(mappedBy="plan")
	@Order(column="plan_goal_idx")
	@Getter
	protected List<Goal> goals  = new ArrayList<>();

	Plan() {
	}

	public Plan(String name) {
		setName(name);
	}
	
	public String title(){
		return getName();
	}

	public Plan(Organisation organisation, String name) {
		setOrganisation(organisation);
		setName(name);
	}
	
	public Plan addGoal(@ParameterLayout(named="Goal name") String name, @ParameterLayout(named="Primary Aim") Aim aim) {
		Goal goal = organisationRepository.createGoal(this, name, aim);
		this.getGoals().add(goal);
		this.getOrganisation().getGoals().add(goal);
		return this;
	}
	
	public List<Aim> choices1AddGoal(){
		return this.getOrganisation().getAims();
	}
	
	public String disableAddGoal(){
		if(this.getOrganisation().getAims().size() == 0)
			return "A Goal must be linked to at least one Aim, so add an Organisation Aim first";
		else
			return null;
	}
	
	@Inject
	OrganisationRepository organisationRepository;

}
