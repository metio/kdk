/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.workloads.ServicePort;

import java.util.Optional;

import static wtf.metio.kdk.construct.service.HTTPIngressRuleValue.httpIngressRuleValue;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#ingressrule-v1beta1-networking-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface IngressRule {

    //region Builders
    static IngressRule of(final String host, final HTTPIngressRuleValue http) {
        return ImmutableIngressRule.builder()
                .host(host)
                .http(http)
                .build();
    }

    static IngressRule of(final String host, final String serviceName, final ServicePort servicePort) {
        final var backend = IngressBackend.of(serviceName, servicePort);
        final var path = HTTPIngressPath.of(backend);
        final var http = httpIngressRuleValue(path);
        return of(host, http);
    }
    //endregion

    @Value.Parameter
    Optional<String> host();

    @Value.Parameter
    HTTPIngressRuleValue http();

}
