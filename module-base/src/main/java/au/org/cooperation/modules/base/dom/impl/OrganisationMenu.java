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
import org.apache.isis.applib.services.bookmark.BookmarkService;
import org.apache.isis.applib.services.eventbus.ActionDomainEvent;

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "cooperation.OrganisationMenu", repositoryFor = Organisation.class)
@DomainServiceLayout(named = "My Organisations", menuOrder = "10")
public class OrganisationMenu {

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	@MemberOrder(sequence = "1")
	public List<Organisation> listAll() {
		return organisationRepo.listAll();
	}

	@Action(semantics = SemanticsOf.SAFE)
	@ActionLayout(bookmarking = BookmarkPolicy.AS_ROOT)
	@MemberOrder(sequence = "2")
	public Organisation switchOrganisation(Organisation organisation) {
		personRepo.currentPerson()
				.setOrgPerson(organisationRepo.findOrganisationPerson(organisation, personRepo.currentPerson()));
		//bookmarks.
		return organisation;
	}

	public List<Organisation> choices0SwitchOrganisation() {
		return personRepo.currentPerson().getLinkedOrganisations(false, false);
	}

	public static class CreateDomainEvent extends ActionDomainEvent<OrganisationMenu> {
	}

	@Action(domainEvent = CreateDomainEvent.class)
	@MemberOrder(sequence = "3")
	public Organisation create(@ParameterLayout(named = "Name") final String name) {
		return organisationRepo.createOrganisation(name);
	}

	@javax.inject.Inject
	OrganisationRepository organisationRepo;
	@javax.inject.Inject
	PersonRepository personRepo;
	@javax.inject.Inject
	BookmarkService bookmarks;
}
