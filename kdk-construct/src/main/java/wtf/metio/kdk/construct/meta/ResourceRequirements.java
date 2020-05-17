/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#resourcerequirements-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface ResourceRequirements {

    //region Builders
    static ImmutableResourceRequirements.Builder builder() {
        return ImmutableResourceRequirements.builder();
    }

    static ResourceRequirements of(
            final ResourceConstraint limits,
            final ResourceConstraint requests) {
        return builder().limits(limits).requests(requests).build();
    }
    //endregion

    /**
     * Limits describes the maximum amount of compute resources allowed.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/configuration/manage-compute-resources-container/">k8s docs</a>
     */
    Optional<ResourceConstraint> limits();

    /**
     * Requests describes the minimum amount of compute resources required. If Requests is omitted for a container, it
     * defaults to Limits if that is explicitly specified, otherwise to an implementation-defined value.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/configuration/manage-compute-resources-container/">k8s docs</a>
     */
    Optional<ResourceConstraint> requests();

}
