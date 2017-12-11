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

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.services.user.UserService;
import org.apache.isis.applib.value.DateTime;
import org.isisaddons.module.security.dom.user.ApplicationUser;
import org.isisaddons.module.security.dom.user.ApplicationUserRepository;

import au.org.cooperation.modules.base.dom.impl.OrganisationPerson.OrganisationPersonStatus;

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

	public OrganisationPerson createOrganisationPerson(Organisation organisation, Person person,
			OrganisationPersonStatus status) {
		final OrganisationPerson object = new OrganisationPerson(organisation, person, status);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		if(person.getOrganisation() == null)
			person.setOrganisation(organisation);
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

	public Goal createGoal(Plan plan, String name, Aim aim) {
		final Goal object = new Goal(plan.getOrganisation(), name, aim);
		object.setPlan(plan);
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
		final Outcome outcome = new Outcome(task.getOrganisation(), goal, description);
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
		final Outcome outcome = new Outcome(result.getOrganisation(), goal, description);
		serviceRegistry.injectServicesInto(outcome);
		repositoryService.persist(outcome);
		result.getOutcomes().add(outcome);
		outcome.getResults().add(result);
		return outcome;
	}

	public Success createSuccess(final String name) {
		final Success object = new Success(this.currentOrganisation(), name);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	public Organisation currentOrganisation() {
		ApplicationUser user = userRepo.findByUsernameCached(users.getUser().getName());
		if (user != null && user instanceof Person)
			return ((Person) user).getOrganisation();
		else
			return null;
	}

	@Inject
	RepositoryService repositoryService;
	@Inject
	ServiceRegistry2 serviceRegistry;
	@Inject
	ApplicationUserRepository userRepo;
	@Inject
	UserService users;

}
