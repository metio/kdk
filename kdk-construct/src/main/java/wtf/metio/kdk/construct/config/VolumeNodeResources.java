/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#volumenoderesources-v1-storage-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface VolumeNodeResources {

    //region Builders
    static ImmutableVolumeNodeResources.Builder builder() {
        return ImmutableVolumeNodeResources.builder();
    }
    //endregion

    /**
     * Maximum number of unique volumes managed by the CSI driver that can be used on a node. A volume that is both
     * attached and mounted on a node is considered to be used once, not twice. The same rule applies for a unique
     * volume that is shared among multiple pods on the same node. If this field is not specified, then the supported
     * number of volumes on this node is unbounded.
     */
    int count();

}
