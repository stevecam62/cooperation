package au.org.cooperation.modules.base.dom.impl.tenancy;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.isisaddons.module.security.dom.tenancy.ApplicationTenancyEvaluator;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import au.org.cooperation.modules.base.dom.impl.Person;
import au.org.cooperation.modules.base.dom.impl.Organisation;

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
	public String hides(Object arg0, ApplicationUser arg1) {
		if (((Organisation) arg0).equals(((Person) arg1).getOrganisation()))
			return null;
		else
			return "Organisation access prevented";
	}
}