/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.internal.HasMetaData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#storageclass-v1-storage-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface StorageClass extends HasMetaData {

    //region Builders
    static ImmutableStorageClass.Builder builder() {
        return ImmutableStorageClass.builder();
    }
    //endregion

    /**
     * AllowVolumeExpansion shows whether the storage class allow volume expand
     */
    boolean allowVolumeExpansion();

    // TODO: allowedTopologies();

    /**
     * Dynamically provisioned PersistentVolumes of this storage class are created with these mountOptions, e.g.
     * ["ro", "soft"]. Not validated - mount of the PVs will simply fail if one is invalid.
     */
    List<String> mountOptions();

    /**
     * Parameters holds the parameters for the provisioner that should create volumes of this storage class.
     */
    Map<String, Object> parameters();

    /**
     * Provisioner indicates the type of the provisioner.
     */
    String provisioner();

    /**
     * Dynamically provisioned PersistentVolumes of this storage class are created with this reclaimPolicy. Defaults to
     * Delete.
     */
    Optional<String> reclaimPolicy();

    /**
     * VolumeBindingMode indicates how PersistentVolumeClaims should be provisioned and bound. When unset,
     * VolumeBindingImmediate is used. This field is only honored by servers that enable the VolumeScheduling feature.
     */
    Optional<String> volumeBindingMode();

}
