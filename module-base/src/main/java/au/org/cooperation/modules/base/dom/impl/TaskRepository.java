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

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Task.class
)
public class TaskRepository {

    public List<Task> listAll() {
        return repositoryService.allInstances(Task.class);
    }

    /*public List<Task> findByName(final String name) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Task.class,
                        "findByName",
                        "name", name));
    }*/

	public Task createTask(final String name) {
        final Task object = new Task(name);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}
	
	public Task createTask(final Goal goal, final String name) {
        final Task object = new Task(goal, name);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}
	
	public Effort createEffort(Task task, Person person, Date start, Date end) {
        final Effort object = new Effort(task, person, start, end);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}
	
	public Result createResult(Task task, String description) {
        final Result object = new Result(task, description);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
	}

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;


}
