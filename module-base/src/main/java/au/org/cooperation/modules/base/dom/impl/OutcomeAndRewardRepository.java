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
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Organisation.class
)
public class OutcomeAndRewardRepository {

    public List<Organisation> listAll() {
        return repositoryService.allInstances(Organisation.class);
    }
    
    public Result createResult(final Organisation organisation, final String name) {
        final Result object = new Result();
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
    }
    
    public Goal createReward(final Organisation organisation, final String name, final Aim aim) {
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
    
	public Outcome createOutcome(final Goal goal,  final String name) {
        final Outcome object = new Outcome(goal.getOrganisation(), goal, name);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}
    
    public Success createSuccess(final String name) {
        final Success object = new Success(organisationRepository.currentOrganisation(), name);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
    }
    
    @Inject
    OrganisationRepository organisationRepository;
    @Inject
    RepositoryService repositoryService;
    @Inject
    ServiceRegistry2 serviceRegistry;

}
