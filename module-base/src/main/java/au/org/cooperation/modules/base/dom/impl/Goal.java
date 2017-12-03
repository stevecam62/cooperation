//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.10 at 09:30:22 PM AEST 
//

package au.org.cooperation.modules.base.dom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Goal", propOrder = { "name", "description", "aim", "tasks", "outcomes" })
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Goal {

    @XmlTransient
    @Column(allowsNull="false", name="organisation_id")
	@Getter
	@Setter(value=AccessLevel.PRIVATE)
	protected Organisation organisation;

	@XmlElement(required = true)
	@Column(allowsNull = "false")
	@Getter
	@Setter
	protected String name;
	
	@XmlElement
	@Column(allowsNull = "true")
	@Getter
	@Setter
	protected String description;

    @XmlElement
	@Column(allowsNull = "false", name="aim_id")
	@Getter
	@Setter(value=AccessLevel.PACKAGE)
	protected Aim aim;
    
    @XmlTransient
	@Column(allowsNull = "true", name="plan_id")
	@Getter
	@Setter(value=AccessLevel.PACKAGE)
	protected Plan plan;

	@Persistent(mappedBy = "goal")
	@Order(column="goal_task_idx")
	@Getter
	protected List<Task> tasks;

	@Persistent(mappedBy = "goal")
	@Order(column="goal_outcome_idx")
	@Getter
	protected List<Outcome> outcomes;

	Goal() {
	}

	public Goal(Organisation organisation, String name, Aim aim) {
		setOrganisation(organisation);
		setName(name);
		setAim(aim);
	}
	
	public String title(){
		return getName();
	}

	public Goal addTask(@ParameterLayout(named="Task name") String name) {
		this.getTasks().add(taskRepository.createTask(this, name));
		return this;
	}

    @XmlTransient
	@Inject
	OrganisationRepository organisationRepository;
	
    @XmlTransient
	@Inject
	TaskRepository taskRepository;

}