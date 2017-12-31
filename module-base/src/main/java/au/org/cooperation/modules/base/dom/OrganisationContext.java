package au.org.cooperation.modules.base.dom;

import au.org.cooperation.modules.base.dom.impl.Organisation;
import au.org.cooperation.modules.base.dom.impl.OrganisationPerson;

public interface OrganisationContext {

	public Organisation getOrganisation();
	
	public String disables(OrganisationPerson orgPerson);
	
	public String hides(OrganisationPerson orgPerson);
}
