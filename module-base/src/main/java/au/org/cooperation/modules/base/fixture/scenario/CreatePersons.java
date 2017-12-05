package au.org.cooperation.modules.base.fixture.scenario;

import java.io.InputStream;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.org.cooperation.modules.base.dom.impl.ObjectFactory;
import au.org.cooperation.modules.base.dom.impl.Person;
import au.org.cooperation.modules.base.dom.impl.PersonMenu;
import au.org.cooperation.modules.base.dom.impl.Persons;


public class CreatePersons extends FixtureScript {

	public CreatePersons() {
		withDiscoverability(Discoverability.DISCOVERABLE);
	}

	private Person person = null;

	@Override
	protected void execute(ExecutionContext ec) {

		try {
			// import object graph from XML
			InputStream is = this.getClass().getResourceAsStream("/au/org/cooperation/modules/base/fixture/persons.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
			Persons _persons = (Persons) JAXBIntrospector.getValue(jaxbUnmarshaller.unmarshal(is));
			for (Person _person : _persons.getPersons()) {
				//this.person = personMenu.create("", null, null);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Person getPerson() {
		return this.person;
	}

	
	@Inject
	PersonMenu personMenu;
	
}

