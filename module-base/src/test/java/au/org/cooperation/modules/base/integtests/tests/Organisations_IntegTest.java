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
package au.org.cooperation.modules.base.integtests.tests;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import au.org.cooperation.modules.base.dom.impl.PersonMenu;
import au.org.cooperation.modules.base.fixture.scenario.CreateOrganisations;
import au.org.cooperation.modules.base.fixture.teardown.OrganisationsTearDown;
import au.org.cooperation.modules.base.integtests.SimpleModuleIntegTestAbstract;

import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.services.xactn.TransactionService;

import static org.assertj.core.api.Assertions.assertThat;

public class Organisations_IntegTest extends SimpleModuleIntegTestAbstract {

	   @Inject
	    FixtureScripts fixtureScripts;
	    @Inject
	    PersonMenu simpleObjectMenu;
	    @Inject
	    TransactionService transactionService;

	    au.org.cooperation.modules.base.dom.impl.Organisation organisation;

	    @Before
	    public void setUp() throws Exception {
	        // given
	        fixtureScripts.runFixtureScript(new OrganisationsTearDown(), null);
	        CreateOrganisations fs = new CreateOrganisations();
	        fixtureScripts.runFixtureScript(fs, null);
	        transactionService.nextTransaction();
	        organisation = fs.getOrganisation();
	        assertThat(organisation).isNotNull();
	    }

	    public static class Name extends Organisations_IntegTest {

	        @Test
	        public void accessible() throws Exception {
	            // when
	            final String name = wrap(organisation).getName();

	            // then
	            assertThat(name).isEqualTo("Our Cooperative Organisation");
	        }
	    }

	}