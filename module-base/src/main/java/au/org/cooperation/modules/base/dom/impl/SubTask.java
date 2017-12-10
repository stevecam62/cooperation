package au.org.cooperation.modules.base.dom.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

//@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
//@Inheritance(strategy=InheritanceStrategy.SUPERCLASS_TABLE)
//@Discriminator(value="SUBTASK")
//@DomainObject(objectType="cooperation.SubTask")
public class SubTask extends Task{

	@Column(allowsNull = "false", name = "parent_task_id")
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	private Task parent;
	
	SubTask(){}
	
	public SubTask(Task parent, String name){
		super(parent.getOrganisation(), parent.getGoal(), name);
		setParent(parent);
	}
}
