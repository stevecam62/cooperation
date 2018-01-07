/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
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
package cooperation.application.services.homepage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.Nature;
import org.apache.isis.applib.services.i18n.TranslatableString;

import au.org.cooperation.modules.base.dom.impl.Organisation;
import au.org.cooperation.modules.base.dom.impl.OrganisationRepository;
import au.org.cooperation.modules.base.dom.impl.simple.SimpleObject;
import au.org.cooperation.modules.base.dom.impl.simple.SimpleObjectRepository;

@DomainObject(nature = Nature.VIEW_MODEL, objectType = "cooperation.HomePageViewModel")
public class HomePageViewModel {

	public String title() {
		// return TranslatableString.tr("{num} objects", "num",
		// getObjects().size());
		return "TEST";
	}

	public List<Organisation> getObjects() {
		Organisation o = organisationRepo.currentOrganisation();
		List<Organisation> list = new ArrayList<>();
		if (o != null)
			list.add(o);
		return list;
	}

	@javax.inject.Inject
	OrganisationRepository organisationRepo;
}
