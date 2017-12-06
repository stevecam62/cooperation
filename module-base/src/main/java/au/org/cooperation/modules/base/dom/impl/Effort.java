package au.org.cooperation.modules.base.dom.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.joda.time.DateTime;

import au.org.cooperation.modules.base.dom.StartAndFinishDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Effort extends StartAndFinishDateTime {
	
    @Column(allowsNull="false", name="organisation_id")
    @Getter 
    @Setter(value=AccessLevel.PRIVATE)
	private Organisation organisation;
    
	@Column(allowsNull = "true")
	//hidden from UI
	@Getter(value=AccessLevel.PACKAGE)
	@Setter(value=AccessLevel.PACKAGE)
	private Reward reward;

	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private Result result;

	@Column(allowsNull = "false")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private Person person;

	@Column(allowsNull = "true")
	@Getter
	@Setter(value = AccessLevel.PROTECTED)
	private Task task;

	/*@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	@Column(allowsNull = "true")
	@Getter
	@Setter
	protected Date start;

	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(Adapter1.class)
	@XmlSchemaType(name = "dateTime")
	@Column(allowsNull = "true")
	@Getter
	@Setter
	protected Date end;*/

	Effort() {
	}

	public Effort(Organisation organisation, Task task, Person person, DateTime start, DateTime end) {
		setOrganisation(organisation);
		setPerson(person);
		setTask(task);
		setStartDateTime(start);
		setEndDateTime(end);
	}
	
	public String title(){
		return "Effort by " + this.getPerson().title();
	}

	public Effort assignToResult(Result result) {
		this.setResult(result);
		this.getTask().getResults().add(result);
		return this;
	}
	
	public String disableAssignToResult(){
		if(this.getTask() == null || this.getTask().getResults().size() == 0)
			return "No parent Task or Results associated with the parent Task";
		else
			return null;
	}
	
	public List<Result> choices0AssignToResult(){
		if(this.getTask() != null && this.getTask().getResults().size() > 0)
			return this.getTask().getResults();
		else
			return null;		
	}

	public Effort addResult(@ParameterLayout(named = "Result description") String description) {
		Result result = taskRepository.createResult(this.getTask(), description);
		this.setResult(result);
		this.getTask().getResults().add(result);
		return this;
	}
	
	@Inject
	TaskRepository taskRepository;

}
