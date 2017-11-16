package au.org.cooperation.modules.base.dom.impl;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Aim", propOrder = { "name" })
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Aim {

	@XmlElement(required = true)
    @Column(allowsNull="false")
    @Getter 
    @Setter
	private String name;
	
    @XmlTransient
    @Column(allowsNull="false", name="organisation_id")
    @Getter 
    @Setter(value=AccessLevel.PRIVATE)
	private Organisation organisation;
	
	public Aim(){
	}
	
	public Aim(Organisation organisation, String name){
		setName(name);
	}
	
	public String title(){
		return getName();
	}

}
