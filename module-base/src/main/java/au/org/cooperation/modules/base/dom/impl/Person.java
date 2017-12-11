/*
 *
 *  Copyright 2017 Alexander Stephen Cameron
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package au.org.cooperation.modules.base.dom.impl;

import java.util.List;

import javax.jdo.annotations.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.isis.applib.annotation.*;
import org.isisaddons.module.security.dom.user.ApplicationUser;

import lombok.*;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@DomainObject()
@XmlJavaTypeAdapter(org.apache.isis.schema.utils.jaxbadapters.PersistentEntityAdapter.class)
public class Person extends ApplicationUser {
	
	/*
	 * Allow a default Organisation to be set on the current user.
	 * 
	 * Organisations have a list of linked users/Persons, but one user might link to
	 * multiple Organisations, but we want to restrict the visibility to one Organisation at a time.
	 * 
	 * We restrict access to all data, but this requires
	 * a current Organisation, that is set here at login, by the user having only one link to an 
	 * Organisation, or, the user selecting one specifically. In the later case the currently operating
	 * one will be saved from session to session.
	 * 
	 */
    @Column(allowsNull="true", name="organisation_id")
    @Getter 
    @Setter(value=AccessLevel.PACKAGE)
	private Organisation organisation;

    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private String familyName;
    
    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private String givenName;
    
    @Column(allowsNull="true") //really false but security module adds 1 user
    @Getter 
    @Setter
    private java.sql.Date dateOfBirth;

    //@XmlElement
    //@Column(allowsNull="true")
    //@Getter 
    //@Setter
    //private Algorithm algorithm;
    
	@Persistent
	@Join
	@Getter
	private List<Task> tasks;
	
	@Persistent(mappedBy="person")
	@Getter
	private List<Effort> efforts;
	
	@Persistent(mappedBy="person")
	@Getter
	private List<Reward> rewards;
    
    public String title(){
    	return this.getGivenName() + " " + getFamilyName();
    }

}
