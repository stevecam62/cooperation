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

package au.org.cooperation.modules.base.fixture.teardown;

import java.util.List;
import java.util.Map;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class OrganisationsTearDown extends FixtureScript {

	@Override
	protected void execute(ExecutionContext executionContext) {
		try {
			Integer result = isisJdoSupport.executeUpdate("delete from cooperation.reward");
			result = isisJdoSupport.executeUpdate("delete from cooperation.result_outcomes");
			result = isisJdoSupport.executeUpdate("delete from cooperation.outcome_results");
			result = isisJdoSupport.executeUpdate("delete from cooperation.effort");
			result = isisJdoSupport.executeUpdate("delete from cooperation.result");
			result = isisJdoSupport.executeUpdate("delete from cooperation.outcome");
			result = isisJdoSupport.executeUpdate("delete from cooperation.person_tasks");
			result = isisJdoSupport.executeUpdate("delete from cooperation.task_persons");
			// isisJdoSupport.executeUpdate("delete from cooperation.person");
			result = isisJdoSupport.executeUpdate("update cooperation.task set parent_task_id = null");
			result = isisJdoSupport.executeUpdate("delete from cooperation.task");
			result = isisJdoSupport.executeUpdate("delete from cooperation.goal");
			result = isisJdoSupport.executeUpdate("delete from cooperation.aim");
			result = isisJdoSupport.executeUpdate("delete from cooperation.plan");
			result = isisJdoSupport.executeUpdate("update cooperation.person set org_person_id = null");
			result = isisJdoSupport.executeUpdate("delete from cooperation.organisation_person");
			result = isisJdoSupport.executeUpdate("delete from cooperation.organisation");
			result = isisJdoSupport.executeUpdate("delete from cooperation.person where id <> 1");
			result = isisJdoSupport.executeUpdate("delete from cooperation.applicationuserroles where userid <> 1");
			result = isisJdoSupport.executeUpdate("delete from cooperation.applicationuser where id <> 1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@javax.inject.Inject
	private IsisJdoSupport isisJdoSupport;

}
