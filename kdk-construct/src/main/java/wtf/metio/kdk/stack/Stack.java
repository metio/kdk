/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.stack;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.workloads.Deployment;
import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.construct.service.Service;
import wtf.metio.kdk.construct.config.ConfigMap;
import wtf.metio.kdk.construct.config.Secret;
import wtf.metio.kdk.construct.config.Volume;

import java.util.List;

/**
 * Encapsulates multiple resources.
 */
@Value.Immutable
public interface Stack {

    //region Builders
    static ImmutableStack.Builder builder() {
        return ImmutableStack.builder();
    }

    static Stack stack(final Deployment deployment, final Service service) {
        return builder()
                .addDeployment(deployment)
                .addService(service)
                .build();
    }

    static Stack stack(final Deployment deployment, final Service service, final Ingress ingress) {
        return builder()
                .addDeployment(deployment)
                .addService(service)
                .addIngress(ingress)
                .build();
    }
    //endregion

    List<Deployment> deployment();

    List<Service> service();

    List<Ingress> ingress();

    List<Secret> secrets();

    List<ConfigMap> configMaps();

    List<Volume> volumes();

}
