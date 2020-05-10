/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public interface ServicePort {

    //region Builders
    static ImmutableServicePort.Builder builder() {
        return ImmutableServicePort.builder();
    }

    static ServicePort of(final int port) {
        return builder().port(port).build();
    }
    //endregion

    Optional<String> name();

    Optional<String> protocol();

    Optional<Integer> nodePort();

    Integer port();

    Optional<Integer> targetPort();

    //region Asserts
    @Value.Check
    default void check() {
        assert port() >= 0;
    }
    //endregion

}
