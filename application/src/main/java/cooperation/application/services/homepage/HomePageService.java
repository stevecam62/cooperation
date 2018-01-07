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
import java.util.List;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import au.org.cooperation.modules.base.dom.impl.Organisation;
import au.org.cooperation.modules.base.dom.impl.OrganisationRepository;

@DomainService(
        nature = NatureOfService.DOMAIN , // trick to suppress the actions from the top-level menu
        objectType = "cooperation.HomePageService"
)
public class HomePageService {

    @Action(semantics = SemanticsOf.SAFE)
    @HomePage
    public Organisation homePage() {
        return organisationRepo.currentOrganisation();
    }

    @javax.inject.Inject
    OrganisationRepository organisationRepo;

}
