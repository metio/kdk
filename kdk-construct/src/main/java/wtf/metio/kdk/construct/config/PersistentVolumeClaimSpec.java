/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.meta.LabelSelector;
import wtf.metio.kdk.construct.meta.ResourceRequirements;
import wtf.metio.kdk.construct.meta.TypedLocalObjectReference;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#persistentvolumeclaimspec-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface PersistentVolumeClaimSpec {

    //region Builders
    static ImmutablePersistentVolumeClaimSpec.Builder builder() {
        return ImmutablePersistentVolumeClaimSpec.builder();
    }
    //endregion

    /**
     * AccessModes contains the desired access modes the volume should have.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/storage/persistent-volumes#access-modes-1">k8s docs</a>
     */
    List<String> accessModes();

    /**
     * This field requires the VolumeSnapshotDataSource alpha feature gate to be enabled and currently VolumeSnapshot
     * is the only supported data source. If the provisioner can support VolumeSnapshot data source, it will create a
     * new volume and data will be restored to the volume at the same time. If the provisioner does not support
     * VolumeSnapshot data source, volume will not be created and the failure will be reported as an event. In the
     * future, we plan to support more data source types and the behavior of the provisioner may change.
     */
    Optional<TypedLocalObjectReference> dataSource();

    /**
     * Resources represents the minimum resources the volume should have.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/storage/persistent-volumes#resources">k8s docs</a>
     */
    Optional<ResourceRequirements> resources();

    /**
     * A label query over volumes to consider for binding.
     */
    Optional<LabelSelector> selector();

    /**
     * Name of the StorageClass required by the claim.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/storage/persistent-volumes#class-1">k8s docs</a>
     */
    Optional<String> storageClassName();

    /**
     * volumeMode defines what type of volume is required by the claim. Value of Filesystem is implied when not included
     * in claim spec. This is a beta feature.
     */
    Optional<String> volumeMode();

    /**
     * VolumeName is the binding reference to the PersistentVolume backing this claim.
     */
    Optional<String> volumeName();

}
