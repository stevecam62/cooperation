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
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.DateTime;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = Person.class
)
public class PersonRepository {

    public List<Person> listAll() {
        return repositoryService.allInstances(Person.class);
    }

    /*public List<Person> findByName(final String name) {
        return repositoryService.allMatches(
                new QueryDefault<>(
                        Person.class,
                        "findByName",
                        "name", name));
    }*/

    public Person createPerson(String givenName, String familyName, Date dateOfBirth) {
        final Person object = new Person();
        object.setGivenName(givenName);
        object.setFamilyName(familyName);
        object.setDateOfBirth(dateOfBirth);
        serviceRegistry.injectServicesInto(object);
        repositoryService.persist(object);
        return object;
    }
  

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;

}
