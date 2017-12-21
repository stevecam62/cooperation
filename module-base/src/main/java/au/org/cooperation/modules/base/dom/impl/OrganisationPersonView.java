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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "Person")
@XmlType(propOrder = { "person", "isAdministrator" })
@XmlAccessorType(XmlAccessType.FIELD)
public class OrganisationPersonView {
	
	@XmlElement(required = true)
	@Getter(value=AccessLevel.PRIVATE)
	@Setter(value=AccessLevel.PACKAGE)
	private Person person;
	
	@XmlElement(required = true)
	@Getter()
	@Setter(value=AccessLevel.PACKAGE)
	private boolean isAdministrator;

	public OrganisationPersonView() {
	}

	public OrganisationPersonView(OrganisationPerson orgPerson) {
		this.setPerson(orgPerson.getPerson());
		this.setAdministrator(orgPerson.isAdministrator());
	}
	
	public String title(){
		return this.getPerson().title();
	}
	
	public String getName(){
		return this.getPerson().getName();
	}
}
