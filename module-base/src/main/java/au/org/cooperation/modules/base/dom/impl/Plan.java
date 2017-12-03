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
import org.apache.isis.applib.annotation.ParameterLayout;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Java class for Plan complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Plan"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="goal" type="{http://www.example.org/OneIdSchema}Goal" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Plan", propOrder = { "name", "description", "goal" })
@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Plan {

	@XmlElement(required = true)
	@Column(allowsNull = "false")
	@Getter
	@Setter
	protected String name;
	
	@XmlElement(required = true)
	@Column(allowsNull = "true")
	@Getter
	@Setter
	protected String description;

	@XmlTransient
	@Column(allowsNull = "false")
	@Getter
	@Setter(value=AccessLevel.PRIVATE)
	protected Organisation organisation;

	@XmlElement(required = true)
	@Persistent(mappedBy="plan")
	@Order(column="plan_goal_idx")
	@Getter
	protected List<Goal> goals;

	Plan() {
	}

	public Plan(String name) {
		setName(name);
	}
	
	public String title(){
		return getName();
	}

	public Plan(Organisation organisation, String name) {
		setOrganisation(organisation);
		setName(name);
	}
	
	public Plan addGoal(@ParameterLayout(named="Goal name") String name, @ParameterLayout(named="Primary Aim") Aim aim) {
		Goal goal = organisationRepository.createGoal(this, name, aim);
		this.getGoals().add(goal);
		this.getOrganisation().getGoals().add(goal);
		return this;
	}
	
	public List<Aim> choices1AddGoal(){
		return this.getOrganisation().getAims();
	}
	
	public String disableAddGoal(){
		if(this.getOrganisation().getAims().size() == 0)
			return "A Goal must be linked to at least one Aim, so add an Organisation Aim first";
		else
			return null;
	}
	
	@XmlTransient
	@Inject
	OrganisationRepository organisationRepository;

}