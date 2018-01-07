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
package cooperation.application.fixture.scenarios;

import javax.annotation.Nullable;

import org.apache.isis.applib.fixturescripts.FixtureScript;

import au.org.cooperation.modules.base.fixture.scenario.CreateOrganisations;
import au.org.cooperation.modules.base.fixture.teardown.OrganisationsTearDown;
import lombok.Getter;
import lombok.Setter;

public class CooperationAppDemo extends FixtureScript {

    public CooperationAppDemo() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Nullable
    @Getter @Setter
    private Integer number;

    @Override
    protected void execute(final ExecutionContext ec) {

        // execute
        ec.executeChild(this, new OrganisationsTearDown());
        ec.executeChild(this, new CreateOrganisations());

    }
}
