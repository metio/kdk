/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.workloads.ServicePort;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#ingressbackend-v1beta1-networking-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface IngressBackend {

    //region Builders
    static IngressBackend of(final String serviceName, final ServicePort servicePort) {
        return ImmutableIngressBackend.of(serviceName, servicePort);
    }
    //endregion

    @Value.Parameter
    String serviceName();

    @Value.Parameter
    ServicePort servicePort();

}
