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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Programmatic;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Reward {

	@Column(allowsNull = "false")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	protected Organisation organisation;

	@Column(allowsNull = "false")
	@Getter
	@Setter(value = AccessLevel.PRIVATE)
	private Person person;

	@Column(allowsNull = "false")
	@Getter
	@Setter()
	protected Date timestamp;

	@Column(allowsNull = "true")
	@Getter
	@Setter()
	protected Float value;

	@Column(allowsNull = "true")
	@Getter
	@Setter()
	protected String description;

	@Persistent(mappedBy = "reward")
	@Join()
	@Getter()
	@Setter()
	// TODO why must this be initialised???
	protected List<Effort> efforts = new ArrayList<>();

	Reward() {
	}

	public Reward(Organisation organisation, Person person) {
		setOrganisation(organisation);
		setPerson(person);
	}

	@Programmatic
	public void addEffort(Effort effort) {
		effort.setReward(this);
		this.getEfforts().add(effort);
	}
}
