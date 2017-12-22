package au.org.cooperation.modules.base.dom.impl.tenancy;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyEvaluator;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import au.org.cooperation.modules.base.dom.impl.Person;
import au.org.cooperation.modules.base.dom.impl.Organisation;
import au.org.cooperation.modules.base.dom.impl.OrganisationRepository;

@DomainService(nature = NatureOfService.DOMAIN)
public class TenancyPathEvaluatorForCooperation implements ApplicationTenancyEvaluator {
	@Override
	public boolean handles(final Class<?> cls) {
		return Organisation.class == cls;
	}

	@Override
	public String disables(Object arg0, ApplicationUser arg1) {
		return null;
	}

	@Override
	public String hides(Object obj, ApplicationUser user) {
		Organisation currentOrg = ((Person) user).getCurrentOrganisation();
		if (((Organisation) obj).equals(currentOrg)) {
			return null;
		} else {
			for (Organisation org : ((Person) user).getLinkedOrganisations(false,false)) {
				if (((Organisation) obj).equals(org)) {
					return null;
				}
			}
		}
		return "Organisation access prevented";
	}

	@Inject
	OrganisationRepository organisationRepo;
}