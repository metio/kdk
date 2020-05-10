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
public interface TypedLocalObjectReference {

    //region Builders
    static ImmutableTypedLocalObjectReference.Builder builder() {
        return ImmutableTypedLocalObjectReference.builder();
    }

    static TypedLocalObjectReference of(final String kind, final String name) {
        return ImmutableTypedLocalObjectReference.of(kind, name);
    }
    //endregion

    /**
     * APIGroup is the group for the resource being referenced. If APIGroup is not specified, the specified Kind must be
     * in the core API group. For any other third-party types, APIGroup is required.
     */
    Optional<String> apiGroup();

    /**
     * Kind is the type of resource being referenced
     */
    @Value.Parameter
    String kind();

    /**
     * Name is the name of resource being referenced
     */
    @Value.Parameter
    String name();

}
