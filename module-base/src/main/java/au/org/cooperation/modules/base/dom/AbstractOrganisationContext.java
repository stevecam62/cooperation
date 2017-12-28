package au.org.cooperation.modules.base.dom;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import au.org.cooperation.modules.base.dom.impl.Organisation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable()
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractOrganisationContext implements OrganisationContext{

		@Column(allowsNull = "false", name = "organisation_id")
		@Getter
		@Setter(value = AccessLevel.PROTECTED)
		private Organisation organisation;
}
