/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#volume-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface EmptyDirVolumeSource {

    //region Builders
    static ImmutableEmptyDirVolumeSource.Builder builder() {
        return ImmutableEmptyDirVolumeSource.builder();
    }
    //endregion

    /**
     * What type of storage medium should back this directory. The default is "" which means to use the node's default
     * medium. Must be an empty string (default) or Memory.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/storage/volumes#emptydir">k8s docs</a>
     */
    Optional<String> medium();

    /**
     * Total amount of local storage required for this EmptyDir volume. The size limit is also applicable for memory
     * medium. The maximum usage on memory medium EmptyDir would be the minimum value between the SizeLimit specified
     * here and the sum of memory limits of all containers in a pod. The default is nil which means that the limit is
     * undefined.
     * 
     * @see <a href="http://kubernetes.io/docs/user-guide/volumes#emptydir">k8s docs</a>
     */
    Optional<String> sizeLimit();

}
