package domainapp.modules.simple.dom.impl.location;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(nature = NatureOfService.DOMAIN, repositoryFor = Address.class)
public class LocationRepository {

	public Address createAddress() {
		final Address object = new Address();
		serviceRegistry.injectServicesInto(object);
		repositoryService.persist(object);
		return object;
	}

	public void deleteAddress(Address address) {
		repositoryService.remove(address);
	}

	@Inject
	RepositoryService repositoryService;
	@Inject
	ServiceRegistry2 serviceRegistry;
}