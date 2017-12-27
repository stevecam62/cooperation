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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.annotation.DomainObject;

import au.org.cooperation.modules.base.dom.OrganisationContext;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@DomainObject()
public class Aim extends OrganisationContext {

	@Column(allowsNull = "false", length = 100)
	@Getter
	@Setter
	private String name;

	@Column(allowsNull = "true", length = 1000)
	@Getter
	@Setter
	private String description;

	public Aim() {
	}

	public Aim(Organisation organisation, String name) {
		setName(name);
	}

	public String title() {
		return getName();
	}
}
