package cooperation.application;

import java.util.Set;

import org.apache.isis.applib.Module;
import org.apache.isis.applib.ModuleAbstract;

import com.google.common.collect.Sets;

import au.org.cooperation.modules.base.BaseModule;

public class CooperationAppApplicationModule extends ModuleAbstract {

    @Override
    public Set<Module> getDependencies() {
        return Sets.<Module>newHashSet(new BaseModule());
    }

}
