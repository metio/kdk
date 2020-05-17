/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#httpgetaction-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface HTTPGetAction {

    //region Builders
    static ImmutableHTTPGetAction.Builder builder() {
        return ImmutableHTTPGetAction.builder();
    }

    static HTTPGetAction of(final int port, final String path) {
        return builder()
                .port(port)
                .path(path)
                .build();
    }
    //endregion

    Optional<String> scheme();

    Optional<String> host();

    Integer port();

    String path();

    //region Assertions
    @Value.Check
    default void check() {
        assert port() >= 0;
    }
    //endregion

}
