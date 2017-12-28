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

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.SemanticsOf;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;
import org.apache.isis.applib.value.Password;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "cooperation.PersonMenu", repositoryFor = Person.class)
@DomainServiceLayout(named = "Persons", menuOrder = "20")
public class PersonMenu {

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	@MemberOrder(sequence = "1")
	public List<Person> listAll() {
		return personRepo.listAll();
	}

	public static class CreateDomainEvent extends ActionDomainEvent<PersonMenu> {
	}

	@Action(domainEvent = CreateDomainEvent.class)
	@MemberOrder(sequence = "3")
	public Person create(@ParameterLayout(named = "Given Name") final String givenName,
			@ParameterLayout(named = "Family Name") final String familyName,
			@ParameterLayout(named = "Date of Birth") final java.sql.Date dateOfBirth, String username,
			Password password, Password passwordRepeat, String emailAddress) {
		return personRepo.createPerson(givenName, familyName, dateOfBirth, username, password, passwordRepeat,
				emailAddress);
	}

	@javax.inject.Inject
	PersonRepository personRepo;
}
