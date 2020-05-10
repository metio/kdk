/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#ingresstls-v1beta1-networking-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface IngressTLS {

    //region Builders
    static ImmutableIngressTLS.Builder builder() {
        return ImmutableIngressTLS.builder();
    }

    static IngressTLS of(final String secretName, final String... hosts) {
        return builder()
                .addHosts(hosts)
                .secretName(secretName)
                .build();
    }
    //endregion

    List<String> hosts();

    Optional<String> secretName();

}
