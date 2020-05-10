/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#volumemount-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface VolumeMount {

    //region Builders
    static ImmutableVolumeMount.Builder builder() {
        return ImmutableVolumeMount.builder();
    }

    static VolumeMount volumeMount(final String name, final String mountPath) {
        return ImmutableVolumeMount.of(name, mountPath);
    }
    //endregion

    /**
     * Path within the container at which the volume should be mounted. Must not contain ':'.
     */
    @Value.Parameter
    String mountPath();

    /**
     * mountPropagation determines how mounts are propagated from the host to container and the other way around. When
     * not set, MountPropagationNone is used. This field is beta in 1.10.
     */
    Optional<String> mountPropagation();

    /**
     * This must match the Name of a Volume.
     */
    @Value.Parameter
    String name();

    /**
     * Mounted read-only if true, read-write otherwise (false or unspecified). Defaults to false.
     */
    Optional<Boolean> readOnly();

    /**
     * Path within the volume from which the container's volume should be mounted. Defaults to "" (volume's root).
     */
    Optional<String> subPath();

    /**
     * Expanded path within the volume from which the container's volume should be mounted. Behaves similarly to
     * SubPath but environment variable references $(VAR_NAME) are expanded using the container's environment. Defaults
     * to "" (volume's root). SubPathExpr and SubPath are mutually exclusive.
     */
    Optional<String> subPathExpr();

}
