package au.org.cooperation.modules.base.dom.impl.user;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;
import org.isisaddons.module.security.dom.user.ApplicationUser;
import org.isisaddons.module.security.dom.user.ApplicationUserFactory;

import au.org.cooperation.modules.base.dom.impl.Person;

@DomainService(nature=NatureOfService.DOMAIN)
public class MyApplicationUserFactory implements ApplicationUserFactory {

	@Override
	public ApplicationUser newApplicationUser() {
        final ApplicationUser object = new Person();
        serviceRegistry.injectServicesInto(object);
        return object;
	}

    @javax.inject.Inject
    RepositoryService repositoryService;
    @javax.inject.Inject
    ServiceRegistry2 serviceRegistry;
}
