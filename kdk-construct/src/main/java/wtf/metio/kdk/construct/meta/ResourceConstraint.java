/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public interface ResourceConstraint {

    //region Builders
    static ImmutableResourceConstraint.Builder builder() {
        return ImmutableResourceConstraint.builder();
    }

    static ResourceConstraint of(final String cpu, final String memory) {
        return builder().cpu(cpu).memory(memory).build();
    }
    //endregion

    Optional<String> cpu();

    Optional<String> memory();

}
