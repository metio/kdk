/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.meta.ObjectMeta;
import wtf.metio.kdk.construct.meta.Selector;
import wtf.metio.kdk.construct.service.Service;
import wtf.metio.kdk.construct.service.ServiceSpec;
import wtf.metio.kdk.construct.workloads.ServicePort;

import static wtf.metio.kdk.construct.meta.Label.*;
import static wtf.metio.kdk.construct.service.Service.service;
import static wtf.metio.kdk.tests.ObjectMetas.createObjectMeta;

public final class Services {

    public static Service testService() {
        return createService(ServicePort.of(8080));
    }

    public static Service createService(final ServicePort servicePort) {
        final var metadata = createObjectMeta();
        final var serviceSpec = ServiceSpec.of(servicePort,
                Selector.of(k8sName("test")),
                Selector.of(k8sInstance("eu-west-17"))
        );
        return service(metadata, serviceSpec);
    }

    public static Service createConanService() {
        return Service.builder()
                .metadata(ObjectMeta.builder()
                        .name("test")
                        .namespace("example")
                        .build())
                .spec(ServiceSpec.builder()
                        .addPorts(ServicePort.builder()
                                .name("9194")
                                .port(9194)
                                .targetPort(9194)
                                .build())
                        .addPorts(ServicePort.builder()
                                .name("2002")
                                .port(2002)
                                .targetPort(2002)
                                .build())
                        .addSelectors(Selector.of(of("app", "conan")))
                        .build())
                .build();
    }

    private Services() {
        // factory class
    }

}
