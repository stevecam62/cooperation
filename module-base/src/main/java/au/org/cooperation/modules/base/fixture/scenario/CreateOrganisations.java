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
import org.apache.isis.applib.value.Password;

import au.org.cooperation.modules.base.dom.impl.OrganisationMenu;
import au.org.cooperation.modules.base.dom.impl.PersonMenu;
import au.org.cooperation.modules.base.fixture.generated.*;

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
				// goals
				for (Goal _goal : _organisation.getGoal()) {
					Aim _aim = (Aim) JAXBIntrospector.getValue(_goal.getAim().get(0));
					wrap(organisation).addGoal(_goal.getName(), aims.get(_aim.getName()));
					// find the goal just added
					for (au.org.cooperation.modules.base.dom.impl.Goal goal : organisation.getGoals()) {
						if (goal.getName().equals(_goal.getName())) {
							goal.setDescription(_goal.getDescription());
						}
					}
				}
				// tasks for goals
				Map<String, au.org.cooperation.modules.base.dom.impl.Goal> goals = new HashMap<>();
				for (au.org.cooperation.modules.base.dom.impl.Goal goal : organisation.getGoals()) {
					goals.put(goal.getName(), goal);
				}
				for (Goal _goal : _organisation.getGoal()) {
					au.org.cooperation.modules.base.dom.impl.Goal goal = goals.get(_goal.getName());
					for (Task _task : _goal.getTask()) {
						au.org.cooperation.modules.base.dom.impl.Task task = goal.addTask(_task.getName(),
								_task.getDescription());
					}
				}
				// plans
				for (Plan _plan : _organisation.getPlan()) {
					wrap(organisation).addPlan(_plan.getName());
				}
				// people
				for (Person _person : _organisation.getPerson()) {
					au.org.cooperation.modules.base.dom.impl.Person person = wrap(personMenu).create(
							_person.getGivenName(), _person.getFamilyName(),
							new java.sql.Date(_person.getDateOfBirth().getTime()), _person.getUsername(),
							new Password(_person.getPassword()), new Password(_person.getPassword()),
							_person.getEmail());
					organisation.addPerson(person, false, false);
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

	@Inject
	PersonMenu personMenu;

}
