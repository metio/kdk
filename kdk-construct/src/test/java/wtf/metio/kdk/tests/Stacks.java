/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.stack.Stack;

import static wtf.metio.kdk.construct.workloads.ServicePort.of;
import static wtf.metio.kdk.stack.Stack.stack;
import static wtf.metio.kdk.tests.Deployments.createDeployment;
import static wtf.metio.kdk.tests.Ingresses.createIngress;
import static wtf.metio.kdk.tests.Services.createService;

public final class Stacks {

    public static Stack simpleStack() {
        return Stack.builder().build();
    }

    public static Stack publicServiceDeployment() {
        final var servicePort = of(8080);
        return stack(
                createDeployment(servicePort),
                createService(servicePort),
                createIngress(servicePort));
    }

    public static Stack conan() {
        return Stack.builder()
                .addSecrets(Secrets.dockerconfigjson())
                .addDeployment(Deployments.createConanDeployment())
                .addService(Services.createConanService())
                .addIngress(Ingresses.createConanIngress())
                .build();
    }

    private Stacks() {
        // factory class
    }

}
