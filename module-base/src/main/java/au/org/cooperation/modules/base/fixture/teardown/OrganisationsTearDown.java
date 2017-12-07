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

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.services.jdosupport.IsisJdoSupport;

public class OrganisationsTearDown extends FixtureScript {

    @Override
    protected void execute(ExecutionContext executionContext) {
    	isisJdoSupport.executeUpdate("delete from cooperation.task");
    	isisJdoSupport.executeUpdate("delete from cooperation.goal");
    	isisJdoSupport.executeUpdate("delete from cooperation.aim");
    	isisJdoSupport.executeUpdate("delete from cooperation.plan");
    	isisJdoSupport.executeUpdate("delete from cooperation.organisationperson");
    	isisJdoSupport.executeUpdate("delete from cooperation.person where id <> 1"); 
        isisJdoSupport.executeUpdate("delete from cooperation.applicationuserroles where userid <> 1");
        isisJdoSupport.executeUpdate("delete from cooperation.applicationuser where id <> 1");
    	isisJdoSupport.executeUpdate("delete from cooperation.organisation");
    }


    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
