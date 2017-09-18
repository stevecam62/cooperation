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
				this.person = personMenu.create("");
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Person getPerson() {
		return this.person;
	}
/**	
	<?xml version="1.0" encoding="UTF-8"?>
	<tns:persons xmlns:tns="http://au.org.cooperation/base" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.example.org/OneIdSchema cooperation.xsd ">
	  <tns:person>
	    <tns:family-name>tns:family-name</tns:family-name>
	    <tns:given-name>tns:given-name</tns:given-name>
	    <tns:date-of-birth>tns:date-of-birth</tns:date-of-birth>
	    <tns:effort>
	      <tns:start>2001-12-31T12:00:00</tns:start>
	      <tns:end>2001-12-31T12:00:00</tns:end>
	    </tns:effort>
	    <tns:reward>
	      <tns:timestamp>2001-12-31T12:00:00</tns:timestamp>
	    </tns:reward>
	    <tns:algorithm>
	      <tns:name>tns:name</tns:name>
	      <tns:effort>
	        <tns:start>2001-12-31T12:00:00</tns:start>
	        <tns:end>2001-12-31T12:00:00</tns:end>
	      </tns:effort>
	      <tns:outcome>
	        <tns:name>tns:name</tns:name>
	      </tns:outcome>
	      <tns:reward>
	        <tns:timestamp>2001-12-31T12:00:00</tns:timestamp>
	      </tns:reward>
	    </tns:algorithm>
	  </tns:person>
	</tns:persons>
*/
	
	@Inject
	PersonMenu personMenu;
	
}

