package au.org.cooperation.modules.base.dom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.ActionLayout;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.SemanticsOf;

import au.org.cooperation.modules.base.dom.OrganisationContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "OrganisationAdmin")
@XmlType(propOrder = { "organisation" })
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganisationAdmin implements OrganisationContext {
	
	public OrganisationAdmin(){}
	
	public OrganisationAdmin(Organisation organisation){
		this.setOrganisation(organisation);
	}
	
	public String title(){
		return this.getOrganisation().title();
	}

	@XmlElement(required = true)
	@Getter
	@Setter(value = AccessLevel.PACKAGE)
	protected Organisation organisation;
	
	public List<PersonView> getAdministrators() {
		List<PersonView> temp = new ArrayList<>();
		for (Person p : this.getOrganisation().listAdministrators()) {
			temp.add(new PersonView(p));
		}
		return temp;
	}
	
	@Action(semantics = SemanticsOf.NON_IDEMPOTENT)
	@ActionLayout(bookmarking = BookmarkPolicy.NEVER)
	@MemberOrder(sequence = "2")
	public OrganisationPersonView addAdministrator(OrganisationPerson orgPerson) {
		orgPerson.setAdministrator(true);
		return new OrganisationPersonView(orgPerson);
	}

	public List<OrganisationPerson> choices0AddAdministrator() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getOrganisation().listActiveOrganisationPersons()) {
			if (!op.isAdministrator) {
				temp.add(op);
			}
		}
		return temp;
	}
	

	@Action(semantics = SemanticsOf.NON_IDEMPOTENT)
	@ActionLayout(bookmarking = BookmarkPolicy.NEVER)
	@MemberOrder(sequence = "3")
	public OrganisationPersonView removeAdministrator(OrganisationPerson orgPerson) {
		orgPerson.setAdministrator(false);
		return new OrganisationPersonView(orgPerson);
	}

	public List<OrganisationPerson> choices0RemoveAdministrator() {
		List<OrganisationPerson> temp = new ArrayList<>();
		for (OrganisationPerson op : this.getOrganisation().listActiveOrganisationPersons()) {
			if (op.isAdministrator) {
				temp.add(op);
			}
		}
		return temp;
	}
	
	@Programmatic
	public String disables(OrganisationPerson orgPerson) {
		if (orgPerson == null) {
			return "No access";
		} else {
			return (orgPerson.isAdministrator()) ? null : "User is not an administrator of this Organisation";
		}
	}

	@Programmatic
	public String hides(OrganisationPerson orgPerson) {
		if (orgPerson == null) {
			return "No access";
		} else {
			return  null;
		}
	}
	
	@XmlTransient
	@javax.inject.Inject
	PersonRepository personRepo;
	
}
