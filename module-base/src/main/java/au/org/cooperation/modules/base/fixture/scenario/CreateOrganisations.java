package au.org.cooperation.modules.base.fixture.scenario;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.org.cooperation.modules.base.dom.impl.OrganisationMenu;
import au.org.cooperation.modules.base.fixture.generated.Aim;
import au.org.cooperation.modules.base.fixture.generated.Goal;
import au.org.cooperation.modules.base.fixture.generated.ObjectFactory;
import au.org.cooperation.modules.base.fixture.generated.Organisation;
import au.org.cooperation.modules.base.fixture.generated.Organisations;
import au.org.cooperation.modules.base.fixture.generated.Plan;
import au.org.cooperation.modules.base.fixture.generated.Task;

public class CreateOrganisations extends FixtureScript {

	public CreateOrganisations() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private au.org.cooperation.modules.base.dom.impl.Organisation organisation = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass()
					.getResourceAsStream("/au/org/cooperation/modules/base/fixture/organisations.xml");
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
				Map<String, au.org.cooperation.modules.base.dom.impl.Aim> aims = new HashMap<>();
				for (au.org.cooperation.modules.base.dom.impl.Aim aim : organisation.getAims()) {
					aims.put(aim.getName(), aim);
				}
				//goals
				for (Goal _goal : _organisation.getGoal()) {
					Aim _aim = (Aim) JAXBIntrospector.getValue(_goal.getAim().get(0));
					wrap(organisation).addGoal(_goal.getName(), aims.get(_aim.getName()));
				}
				//tasks for goals
				Map<String, au.org.cooperation.modules.base.dom.impl.Goal> goals = new HashMap<>();
				for (au.org.cooperation.modules.base.dom.impl.Goal goal : organisation.getGoals()) {
					goals.put(goal.getName(), goal);
				}
				for (Goal _goal : _organisation.getGoal()) {
					au.org.cooperation.modules.base.dom.impl.Goal goal = goals.get(_goal.getName());
					for(Task _task : _goal.getTask()){
						au.org.cooperation.modules.base.dom.impl.Task task = goal.addTask(_task.getName(), _task.getDescription());
					}
				}
				//plans
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
