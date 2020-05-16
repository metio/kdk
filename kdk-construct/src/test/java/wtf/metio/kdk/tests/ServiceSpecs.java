/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.meta.Selector;
import wtf.metio.kdk.construct.service.ServiceSpec;
import wtf.metio.kdk.construct.workloads.ServicePort;

import static wtf.metio.kdk.construct.meta.Label.k8sInstance;
import static wtf.metio.kdk.construct.meta.Label.k8sName;

public final class ServiceSpecs {

    public static ServiceSpec testServiceSpec() {
        return createServiceSpec(ServicePort.of(8080));
    }

    public static ServiceSpec createServiceSpec(final ServicePort servicePort) {
        final var name = Selector.of(k8sName("test"));
        final var instance = Selector.of(k8sInstance("eu-west-17"));
        return ServiceSpec.of(servicePort, name, instance);
    }

    private ServiceSpecs() {
        // factory class
    }

}
