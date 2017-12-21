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

import java.util.ArrayList;
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

@DomainService(nature = NatureOfService.VIEW_MENU_ONLY, objectType = "cooperation.AdminitratorMenu", repositoryFor = Organisation.class)
@DomainServiceLayout(named = "Organisations", menuOrder = "20")
public class AdministratorMenu {

	@Action(semantics = SemanticsOf.NON_IDEMPOTENT)
	@ActionLayout(bookmarking = BookmarkPolicy.NEVER)
	@MemberOrder(sequence = "1")
	public Organisation current() {
		return organisationRepo.currentOrganisation();
	}

	@Action(semantics = SemanticsOf.NON_IDEMPOTENT)
	@ActionLayout(bookmarking = BookmarkPolicy.NEVER)
	@MemberOrder(sequence = "2")
	public List<PersonView> listAdministrators() {
		List<PersonView> temp = new ArrayList<>();
		for (Person p : organisationRepo.currentOrganisation().listAdministrators()) {
			temp.add(new PersonView(p));
		}
		return temp;
	}
	
	public boolean hideListAdministrators(){
		return !organisationRepo.currentOrganisation().isAdministrator(personRepo.currentPerson());
	}

	@Action(semantics = SemanticsOf.NON_IDEMPOTENT)
	@ActionLayout(bookmarking = BookmarkPolicy.NEVER)
	@MemberOrder(sequence = "2")
	public OrganisationPersonView addAdministrator(OrganisationPerson orgPerson) {
		orgPerson.setAdministrator(true);
		return new OrganisationPersonView(orgPerson);
	}

	public List<OrganisationPerson> choices0AddAdministrator() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : organisationRepo.currentOrganisation().listActiveOrganisationPersons()) {
			if (!op.isAdministrator) {
				temp.add(op);
			}
		}
		return temp;
	}
	
	public boolean hideAddAdministrator(){
		return !organisationRepo.currentOrganisation().isAdministrator(personRepo.currentPerson());
	}

	@Action(semantics = SemanticsOf.NON_IDEMPOTENT)
	@ActionLayout(bookmarking = BookmarkPolicy.NEVER)
	@MemberOrder(sequence = "3")
	public OrganisationPersonView removeAdministrator(OrganisationPerson orgPerson) {
		orgPerson.setAdministrator(false);
		return new OrganisationPersonView(orgPerson);
	}

	public List<OrganisationPerson> choices0RemoveAdministrator() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : organisationRepo.currentOrganisation().listActiveOrganisationPersons()) {
			if (op.isAdministrator) {
				temp.add(op);
			}
		}
		return temp;
	}
	
	public boolean hideRemoveAdministrator(){
		return !organisationRepo.currentOrganisation().isAdministrator(personRepo.currentPerson());
	}

	@javax.inject.Inject
	OrganisationRepository organisationRepo;
	@javax.inject.Inject
	PersonRepository personRepo;

}
