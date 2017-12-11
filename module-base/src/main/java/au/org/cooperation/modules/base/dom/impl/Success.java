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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@DomainObject()
public class Success {

	@Column(allowsNull = "false")
	@Getter
	@Setter(value=AccessLevel.PRIVATE)
	protected Organisation organisation;

	@Column(allowsNull = "false")
	@Getter
	@Setter
	protected String name;

	@Column(allowsNull = "false")
	@Getter
	@Setter
	protected Aim aim;
	
	@Getter
	protected List<Outcome> outcome;
	
	@Getter
	protected List<Goal> goal;

	public Success() {
	}

	public Success( Organisation organisation, String name) {
		setOrganisation(organisation);
		setName(name);
	}
}
