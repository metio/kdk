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
public interface Volume {

    //region Builders
    static ImmutableVolume.Builder builder() {
        return ImmutableVolume.builder();
    }
    //endregion

    // TODO: awsElasticBlockStore()
    // TODO: azureDisk()
    // TODO: azureFile()
    // TODO: cephfs()
    // TODO: cinder()

    /**
     * ConfigMap represents a configMap that should populate this volume
     */
    Optional<ConfigMapVolumeSource> configMap();

    // TODO: csi()
    // TODO: downwardAPI()

    /**
     * EmptyDir represents a temporary directory that shares a pod's lifetime.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/storage/volumes#emptydir">k8s docs</a>
     */
    Optional<EmptyDirVolumeSource> emptyDir();

    // TODO: fc()
    // TODO: flexVolume()
    // TODO: flocker()
    // TODO: gcePersistentDisk()
    // TODO: gitRepo()
    // TODO: glusterfs()
    // TODO: hostPath()
    // TODO: iscsi()

    /**
     * Volume's name. Must be a DNS_LABEL and unique within the pod.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names">k8s docs</a>
     */
    String name();

    // TODO: nfs()

    /**
     * PersistentVolumeClaimVolumeSource represents a reference to a PersistentVolumeClaim in the same namespace.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/storage/persistent-volumes#persistentvolumeclaims">k8s docs</a>
     */
    Optional<PersistentVolumeClaimVolumeSource> persistentVolumeClaim();

    // TODO: photonPersistentDisk()
    // TODO: portworxVolume()
    // TODO: projected()
    // TODO: quobyte()
    // TODO: rbd()
    // TODO: scaleIO()

    /**
     * Secret represents a secret that should populate this volume.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/storage/volumes#secret">k8s docs</a>
     */
    Optional<SecretVolumeSource> secret();

    // TODO: storageos()
    // TODO: vsphereVolume()

}
