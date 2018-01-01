package au.org.cooperation.modules.base.dom.impl.tenancy;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyEvaluator;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import au.org.cooperation.modules.base.dom.impl.Person;
import au.org.cooperation.modules.base.dom.impl.Result;
import au.org.cooperation.modules.base.dom.impl.Task;
import au.org.cooperation.modules.base.dom.OrganisationContext;
import au.org.cooperation.modules.base.dom.impl.Effort;
import au.org.cooperation.modules.base.dom.impl.Organisation;
import au.org.cooperation.modules.base.dom.impl.OrganisationPerson;
import au.org.cooperation.modules.base.dom.impl.OrganisationRepository;

@DomainService(nature = NatureOfService.DOMAIN)
public class TenancyPathEvaluatorForCooperation implements ApplicationTenancyEvaluator {
	@Override
	public boolean handles(final Class<?> cls) {
		if (OrganisationContext.class.isAssignableFrom(cls)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String disables(Object obj, ApplicationUser user) {
		Person person = (Person) user;
		OrganisationContext context = (OrganisationContext) obj;
		Organisation organisation = context.getOrganisation();
		OrganisationPerson orgPerson = null;
		if(person.getCurrentOrganisation() != null && person.getCurrentOrganisation().equals(organisation))
			orgPerson= person.getOrgPerson(null);
		else
			orgPerson = organisationRepo.findOrganisationPerson(organisation, person);
		return context.disables(orgPerson);
	}

	@Override
	public String hides(Object obj, ApplicationUser user) {
		Person person = (Person) user;
		OrganisationContext context = (OrganisationContext) obj;
		Organisation organisation = context.getOrganisation();
		OrganisationPerson orgPerson = null;
		if(person.getCurrentOrganisation() != null && person.getCurrentOrganisation().equals(organisation))
			orgPerson= person.getOrgPerson(null);
		else
			orgPerson = organisationRepo.findOrganisationPerson(organisation, person);
		return context.hides(orgPerson);
	}

	@Inject
	OrganisationRepository organisationRepo;
}