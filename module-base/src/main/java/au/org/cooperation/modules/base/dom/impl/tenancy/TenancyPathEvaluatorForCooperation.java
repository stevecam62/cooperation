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
import au.org.cooperation.modules.base.dom.impl.OrganisationRepository;

@DomainService(nature = NatureOfService.DOMAIN)
public class TenancyPathEvaluatorForCooperation implements ApplicationTenancyEvaluator {
	@Override
	public boolean handles(final Class<?> cls) {
		if (Organisation.class == cls) {
			return true;
		} else if (OrganisationContext.class.isAssignableFrom(cls)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String disables(Object obj, ApplicationUser user) {
		Organisation currentOrg = ((Person) user).getCurrentOrganisation();
		if (obj instanceof Organisation) {
			if (((Organisation) obj).equals(currentOrg)) {
				if (currentOrg.isAdministrator((Person) user)) {
					return null;
				} else {
					return "User is not an administrator of this Organisation";
				}
			}
		} else if (obj instanceof OrganisationContext) {
			if (((OrganisationContext) obj).getOrganisation().equals(currentOrg)) {
				if (currentOrg.isAdministrator((Person) user)) {
					return null;
				} else {
					// ordinary users have access to Task (some actions), Effort and Result only
					if (obj instanceof Task || obj instanceof Effort || obj instanceof Result) {
						return null;
					} else {
						return "User is not an administrator of this Organisation";
					}
				}
			}
		}
		return "No access allowed, relates to wrong Organisation";
	}

	@Override
	public String hides(Object obj, ApplicationUser user) {
		Organisation currentOrg = ((Person) user).getCurrentOrganisation();
		if (obj instanceof Organisation) {
			if (((Organisation) obj).equals(currentOrg)) {
				return null;
			} else {// user may be swapping to a different linked organisation
				for (Organisation org : ((Person) user).getLinkedOrganisations(false, false)) {
					if (((Organisation) obj).equals(org)) {
						return null;
					}
				}
			}
		} else if (obj instanceof OrganisationContext) {
			if (((OrganisationContext) obj).getOrganisation().equals(currentOrg))
				return null;
		}
		return "No access allowed, relates to wrong Organisation";
	}

	@Inject
	OrganisationRepository organisationRepo;
}