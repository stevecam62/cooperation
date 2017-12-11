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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.apache.isis.applib.value.DateTime;
import org.apache.isis.applib.value.Password;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.dom.user.ApplicationUserRepository;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Person.class)
public class PersonRepository {

	public List<Person> listAll() {
		return repositoryService.allInstances(Person.class);
	}

	/*
	 * public List<Person> findByName(final String name) { return
	 * repositoryService.allMatches( new QueryDefault<>( Person.class,
	 * "findByName", "name", name)); }
	 */

	public Person createPerson(String givenName, String familyName, java.sql.Date dateOfBirth, String username,
			Password password, Password passwordRepeat, String emailAddress) {
		final Person object = (Person) userRepository.newLocalUser(username, password, passwordRepeat, 
				roleRepository.findByName("isis-module-security-regular-user"),
				true, emailAddress);
		object.setGivenName(givenName);
		object.setFamilyName(familyName);
		object.setDateOfBirth(dateOfBirth);
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	@Inject
	RepositoryService repositoryService;
	@Inject
	ServiceRegistry2 serviceRegistry;
	@Inject
	ApplicationUserRepository userRepository;
	@Inject
	ApplicationRoleRepository roleRepository;
}
