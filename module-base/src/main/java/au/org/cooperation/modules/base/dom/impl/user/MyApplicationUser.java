package domainapp.modules.simple.dom.impl.user;


import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.InheritanceStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Programmatic;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import domainapp.modules.simple.dom.impl.location.Address;
import domainapp.modules.simple.dom.impl.location.LocationRepository;
import domainapp.modules.simple.dom.impl.location.State;
import lombok.Getter;
import lombok.Setter;


@javax.jdo.annotations.PersistenceCapable()
@javax.jdo.annotations.Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "findByUsername", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.modules.simple.dom.impl.user.MyApplicationUser " + "WHERE username == :username"),
		@javax.jdo.annotations.Query(name = "findByEmailAddress", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.modules.simple.dom.impl.user.MyApplicationUser "
				+ "WHERE emailAddress == :emailAddress"),
		@javax.jdo.annotations.Query(name = "findByAtPath", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.modules.simple.dom.impl.user.MyApplicationUser " + "WHERE atPath == :atPath"),
		@javax.jdo.annotations.Query(name = "findByName", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.modules.simple.dom.impl.user.MyApplicationUser "
				+ "WHERE username.matches(:nameRegex)" + "   || familyName.matches(:nameRegex)"
				+ "   || givenName.matches(:nameRegex)" + "   || knownAs.matches(:nameRegex)"),
		@javax.jdo.annotations.Query(name = "find", language = "JDOQL", value = "SELECT "
				+ "FROM domainapp.modules.simple.dom.impl.user.MyApplicationUser " + "WHERE username.matches(:regex)"
				+ " || familyName.matches(:regex)" + " || givenName.matches(:regex)" + " || knownAs.matches(:regex)"
				+ " || emailAddress.matches(:regex)") })
@DomainObject(objectType = "User")
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
public class MyApplicationUser extends ApplicationUser {

	// public static final String DEFAULT_USER_ROLE = "MyApplicationUser";
	// public static final String PROJECT_ADMIN_ROLE =
	// "OneIdProjectAdministrator";

	@Getter
	@Setter
	@Column(allowsNull = "true")
	private State state;
	@Getter
	@Setter
	@Column(allowsNull = "true")
	private String whiteCardNumber;
	@Getter
	@Setter
	@Column(allowsNull = "true")
	private String rescueEmailAddress;
	@Getter
	@Setter
	@Column(allowsNull = "true")
	private Address streetAddress;
	@Getter
	@Setter
	@Column(allowsNull = "true")
	private Address mailAddress;
	@Getter
	@Setter
	@Column(allowsNull = "true")
	private String mobileNumber;


	@Override
	public String title() {
		return this.getGivenName() + " " + this.getFamilyName();
	}

	@Programmatic
	public String getFullStreetAddress() {
		if (getStreetAddress() == null)
			return "Unknown";
		else
			return getStreetAddress().title();
	}

	@Action()
	public MyApplicationUser updateStreetAddress(
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Street Line 1") String street1,
			@Parameter(optionality = Optionality.OPTIONAL) @ParameterLayout(named = "Street Line 2") String street2,
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Suburb") String suburb,
			@Parameter(optionality = Optionality.OPTIONAL) @ParameterLayout(named = "Postcode") String postcode,
			@Parameter(optionality = Optionality.OPTIONAL) @ParameterLayout(named = "Is Mail Address Too?") boolean isMailAddress) {
		if (this.getStreetAddress() != null)
			locationRepository.deleteAddress(this.getStreetAddress());
		Address newAddress = locationRepository.createAddress();
		newAddress.setStreet1(street1);
		newAddress.setStreet2(street2);
		newAddress.setPostcode(postcode);
		newAddress.setSuburb(suburb);
		this.setStreetAddress(newAddress);
		if (isMailAddress) {
			this.updateMailAddress(street1, street2, suburb, postcode);
		}
		return this;
	}

	public String default0UpdateStreetAddress() {
		return getStreetAddress() != null ? getStreetAddress().getStreet1() : null;
	}

	public String default1UpdateStreetAddress() {
		return getStreetAddress() != null ? getStreetAddress().getStreet2() : null;
	}

	public String default2UpdateStreetAddress() {
		return getStreetAddress() != null ? getStreetAddress().getSuburb() : null;
	}

	public String default3UpdateStreetAddress() {
		return getStreetAddress() != null ? getStreetAddress().getPostcode() : null;
	}

	public boolean default4UpdateStreetAddress() {
		return false;
	}

	public Address getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(final Address mailAddress) {
		this.mailAddress = mailAddress;
	}

	@Programmatic
	public String getFullMailAddress() {
		if (getMailAddress() == null)
			return "Unknown";
		else
			return getMailAddress().title();
	}

	@Action()
	public MyApplicationUser updateMailAddress(
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Street Line 1") String street1,
			@Parameter(optionality = Optionality.OPTIONAL) @ParameterLayout(named = "Street Line 2") String street2,
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Suburb") String suburb,
			@Parameter(optionality = Optionality.OPTIONAL) @ParameterLayout(named = "Postcode") String postcode) {
		if (this.getMailAddress() != null)
			locationRepository.deleteAddress(this.getMailAddress());
		Address newAddress = locationRepository.createAddress();
		newAddress.setStreet1(street1);
		newAddress.setStreet2(street2);
		newAddress.setPostcode(postcode /* suburb.getPostcode().toString() */);
		newAddress.setSuburb(suburb /* suburb.getName() */);
		this.setMailAddress(newAddress);
		return this;
	}

	public String default0UpdateMailAddress() {
		return getMailAddress() != null ? getMailAddress().getStreet1() : null;
	}

	public String default1UpdateMailAddress() {
		return getMailAddress() != null ? getMailAddress().getStreet2() : null;
	}

	public String default2UpdateMailAddress() {
		return getMailAddress() != null ? getMailAddress().getSuburb() : null;
	}

	public String default3UpdateMailAddress() {
		return getMailAddress() != null ? getMailAddress().getPostcode() : null;
	}

	@Inject
	LocationRepository locationRepository;

}
