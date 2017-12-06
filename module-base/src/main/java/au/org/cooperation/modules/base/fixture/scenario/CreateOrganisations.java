package au.org.cooperation.modules.base.fixture.scenario;

import java.io.InputStream;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.org.cooperation.modules.base.dom.impl.OrganisationMenu;
import au.org.cooperation.modules.base.integtests.generated.Aim;
import au.org.cooperation.modules.base.integtests.generated.Goal;
import au.org.cooperation.modules.base.integtests.generated.ObjectFactory;
import au.org.cooperation.modules.base.integtests.generated.Organisation;
import au.org.cooperation.modules.base.integtests.generated.Organisations;
import au.org.cooperation.modules.base.integtests.generated.Plan;


public class CreateOrganisations extends FixtureScript {

	public CreateOrganisations() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private au.org.cooperation.modules.base.dom.impl.Organisation organisation = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/org/cooperation/modules/base/fixture/organisations.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Organisations _organisations = (Organisations) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (Organisation _organisation : _organisations.getOrganisation()) {
				organisation = wrap(organisationMenu).create(_organisation.getName());
				organisation.setDescription(_organisation.getDescription());
				for (Aim _aim : _organisation.getAim()) {
					wrap(organisation).addAim(_aim.getName());
				}
				for (Goal _goal : _organisation.getGoal()) {
					wrap(organisation).addGoal(_goal.getName(),null);
				}
				for (Plan _plan : _organisation.getPlan()) {
					wrap(organisation).addPlan(_plan.getName());
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public au.org.cooperation.modules.base.dom.impl.Organisation getOrganisation() {
		return this.organisation;
	}

	@Inject
	OrganisationMenu organisationMenu;

	
}

