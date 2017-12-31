package au.org.cooperation.modules.base.dom;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.Programmatic;

import au.org.cooperation.modules.base.dom.impl.Organisation;
import au.org.cooperation.modules.base.dom.impl.OrganisationPerson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractOrganisationContext implements OrganisationContext {

	@Column(allowsNull = "false", name = "organisation_id")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private Organisation organisation;

	@Programmatic
	public String disables(OrganisationPerson orgPerson) {
		if (orgPerson == null) {
			return "No access";
		} else {
			return (orgPerson.isAdministrator()) ? null : "User is not an administrator of this Organisation";
		}
	}

	@Programmatic
	public String hides(OrganisationPerson orgPerson) {
		if (orgPerson == null) {
			return "No access";
		} else {
			return  null;
		}
	}
}
