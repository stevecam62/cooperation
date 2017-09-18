package au.org.cooperation.modules.base.dom.impl.user;

import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;

import org.isisaddons.module.security.dom.role.ApplicationRole;
import org.isisaddons.module.security.dom.role.ApplicationRoleRepository;
import org.isisaddons.module.security.userreg.SecurityModuleAppUserRegistrationServiceAbstract;

/**
 * An override of the default impl of {@link org.apache.isis.applib.services.userreg.UserRegistrationService}
 * that uses {@link org.isisaddons.module.security.fixture.scripts.roles.ExampleFixtureScriptsRoleAndPermissions#ROLE_NAME}
 * as initial role
 */
@DomainService(
        nature = NatureOfService.DOMAIN
)
public class MyUserRegistrationService extends SecurityModuleAppUserRegistrationServiceAbstract {

    @Override
    protected ApplicationRole getInitialRole() {
        return findRole("isis-module-security-regular-user");
    }

    @Override
    protected Set<ApplicationRole> getAdditionalInitialRoles() {
        return Collections.singleton(findRole("isis-module-security-regular-user"));
    }

    private ApplicationRole findRole(final String roleName) {
        return applicationRoleRepository.findByName(roleName);
    }


    @Inject
    private ApplicationRoleRepository applicationRoleRepository;
}
