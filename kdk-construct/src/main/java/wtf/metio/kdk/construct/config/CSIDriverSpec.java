/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#csidriver-v1beta1-storage-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface CSIDriverSpec {

    //region Builders
    static ImmutableCSIDriverSpec.Builder builder() {
        return ImmutableCSIDriverSpec.builder();
    }
    //endregion

    /**
     * attachRequired indicates this CSI volume driver requires an attach operation (because it implements the CSI
     * ControllerPublishVolume() method), and that the Kubernetes attach detach controller should call the attach
     * volume interface which checks the volumeattachment status and waits until the volume is attached before
     * proceeding to mounting. The CSI external-attacher coordinates with CSI volume driver and updates the
     * volumeattachment status when the attach operation is complete. If the CSIDriverRegistry feature gate is enabled
     * and the value is specified to false, the attach operation will be skipped. Otherwise the attach operation will
     * be called.
     */
    Optional<Boolean> attachRequired();

    /**
     * If set to true, podInfoOnMount indicates this CSI volume driver requires additional pod information (like
     * podName, podUID, etc.) during mount operations. If set to false, pod information will not be passed on mount.
     * Default is false. The CSI driver specifies podInfoOnMount as part of driver deployment. If true, Kubelet will
     * pass pod information as VolumeContext in the CSI NodePublishVolume() calls. The CSI driver is responsible for
     * parsing and validating the information passed in as VolumeContext. The following VolumeConext will be passed if
     * podInfoOnMount is set to true. This list might grow, but the prefix will be used. "csi.storage.k8s.io/pod.name":
     * pod.Name "csi.storage.k8s.io/pod.namespace": pod.Namespace "csi.storage.k8s.io/pod.uid": string(pod.UID)
     * "csi.storage.k8s.io/ephemeral": "true" iff the volume is an ephemeral inline volume defined by a CSIVolumeSource,
     * otherwise "false" "csi.storage.k8s.io/ephemeral" is a new feature in Kubernetes 1.16. It is only required for
     * drivers which support both the "Persistent" and "Ephemeral" VolumeLifecycleMode. Other drivers can leave pod info
     * disabled and/or ignore this field. As Kubernetes 1.15 doesn't support this field, drivers can only support one
     * mode when deployed on such a cluster and the deployment determines which mode that is, for example via a command
     * line parameter of the driver.
     */
    Optional<Boolean> podInfoOnMount();

    /**
     * VolumeLifecycleModes defines what kind of volumes this CSI volume driver supports. The default if the list is
     * empty is "Persistent", which is the usage defined by the CSI specification and implemented in Kubernetes via the
     * usual PV/PVC mechanism. The other mode is "Ephemeral". In this mode, volumes are defined inline inside the pod
     * spec with CSIVolumeSource and their lifecycle is tied to the lifecycle of that pod. A driver has to be aware of
     * this because it is only going to get a NodePublishVolume call for such a volume. For more information about
     * implementing this mode, see https://kubernetes-csi.github.io/docs/ephemeral-local-volumes.html A driver can
     * support one or more of these modes and more modes may be added in the future.
     */
    List<String> volumeLifecycleModes();

}
