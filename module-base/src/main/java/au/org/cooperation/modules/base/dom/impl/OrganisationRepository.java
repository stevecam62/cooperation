/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
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

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.DateTime;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Organisation.class)
public class OrganisationRepository {

	public List<Organisation> listAll() {
		return repositoryService.allInstances(Organisation.class);
	}

	/*
	 * public List<Organisation> findByName(final String name) { return
	 * repositoryService.allMatches( new QueryDefault<>( Organisation.class,
	 * "findByName", "name", name)); }
	 */

	public Organisation createOrganisation(final String name) {
		final Organisation object = new Organisation(name);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	public Aim createAim(final Organisation organisation, final String name) {
		final Aim object = new Aim(organisation, name);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	public Plan createPlan(final Organisation organisation, final String name) {
		final Plan object = new Plan(organisation, name);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	public Goal createGoal(final Organisation organisation, final String name, final Aim aim) {
		final Goal object = new Goal(organisation, name, aim);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	public Outcome createOutcome(final String name) {
		final Outcome object = new Outcome(name);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	public Outcome createOutcome(final Task task, final String description) {
		if (task == null || description == null)
			return null;
		Goal goal = null;
		if (task != null)
			goal = task.getGoal();
		final Outcome outcome = new Outcome(goal, description);
		serviceRegistry.injectServicesInto(outcome);
		repositoryService.persist(outcome);
		return outcome;
	}

	public Outcome createOutcome(final Result result, final String description) {
		if (result == null || description == null)
			return null;
		Goal goal = null;
		if (result != null && result.getTask() != null && result.getTask().getGoal() != null)
			goal = result.getTask().getGoal();
		final Outcome outcome = new Outcome(goal, description);
		serviceRegistry.injectServicesInto(outcome);
		repositoryService.persist(outcome);
		result.getOutcomes().add(outcome);
		outcome.getResults().add(result);
		return outcome;
	}

	public Success createSuccess(final String name) {
		final Success object = new Success(name);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	@javax.inject.Inject
	RepositoryService repositoryService;
	@javax.inject.Inject
	ServiceRegistry2 serviceRegistry;

}
