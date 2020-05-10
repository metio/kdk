/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#httpingresspath-v1beta1-networking-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface HTTPIngressPath {

    //region Builders
    static ImmutableHTTPIngressPath.Builder builder() {
        return ImmutableHTTPIngressPath.builder();
    }

    static HTTPIngressPath of(final IngressBackend backend) {
        return ImmutableHTTPIngressPath.of(backend);
    }

    static HTTPIngressPath of(final IngressBackend backend, final String path) {
        return ImmutableHTTPIngressPath.builder()
                .path(path)
                .backend(backend)
                .build();
    }
    //endregion

    Optional<String> path();

    @Value.Parameter
    IngressBackend backend();

}
