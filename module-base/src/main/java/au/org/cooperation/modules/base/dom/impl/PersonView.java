package au.org.cooperation.modules.base.dom.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "Person")
@XmlType(propOrder = { "person" })
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonView {
	
	@XmlElement(required = true)
	@Getter(value=AccessLevel.PRIVATE)
	@Setter()
	private Person person;

	public PersonView() {
	}

	public PersonView(Person person) {
		this.setPerson(person);
	}
	
	public String title(){
		return this.getPerson().title();
	}
	
	public String getName(){
		return this.getPerson().getName();
	}
}
