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
 */package au.org.cooperation.modules.base.dom.impl;

import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.*;

import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.ParameterLayout;

import au.org.cooperation.modules.base.dom.AbstractOrganisationContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = "cooperation")
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)
@DomainObject()
public class Result extends AbstractOrganisationContext{

    @Column(allowsNull="false")
    @Getter 
    @Setter
    protected String description;
    
    @Column(allowsNull="false", name="task_id")
    @Getter 
    @Setter(value=AccessLevel.PRIVATE)
    protected Task task;
    
    @Join()
    @Getter
    @Setter
    protected List<Outcome> outcomes;
    
    Result(){}
    
    public Result(Organisation organisation, Task task, String description){
    	setOrganisation(organisation);
    	setTask(task);
    	setDescription(description);
    }
    
    public String title(){
    	return getDescription();
    }
    
    public Result addOutcome(@ParameterLayout(named="Description") String description){
    	Outcome outcome = organisationRepository.createOutcome(this, description);
    	return this;
    }
    
	@Inject
	OrganisationRepository organisationRepository;
}
