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
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#securitycontext-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface SecurityContext {

    //region Builders
    static ImmutableSecurityContext.Builder builder() {
        return ImmutableSecurityContext.builder();
    }
    //endregion

    /**
     * AllowPrivilegeEscalation controls whether a process can gain more privileges than its parent process. This bool
     * directly controls if the no_new_privs flag will be set on the container process. AllowPrivilegeEscalation is true
     * always when the container is: 1) run as Privileged 2) has CAP_SYS_ADMIN
     */
    Optional<Boolean> allowPrivilegeEscalation();

    // TODO: capabilities

    /**
     * Run container in privileged mode. Processes in privileged containers are essentially equivalent to root on the
     * host. Defaults to false.
     */
    Optional<Boolean> privileged();

    /**
     * procMount denotes the type of proc mount to use for the containers. The default is DefaultProcMount which uses
     * the container runtime defaults for readonly paths and masked paths. This requires the ProcMountType feature flag
     * to be enabled.
     */
    Optional<String> procMount();

    /**
     * Whether this container has a read-only root filesystem. Default is false.
     */
    Optional<Boolean> readOnlyRootFilesystem();

    /**
     * The GID to run the entrypoint of the container process. Uses runtime default if unset. May also be set in
     * PodSecurityContext. If set in both SecurityContext and PodSecurityContext, the value specified in SecurityContext
     * takes precedence.
     */
    Optional<Integer> runAsGroup();

    /**
     * Indicates that the container must run as a non-root user. If true, the Kubelet will validate the image at runtime
     * to ensure that it does not run as UID 0 (root) and fail to start the container if it does. If unset or false, no
     * such validation will be performed. May also be set in PodSecurityContext. If set in both SecurityContext and
     * PodSecurityContext, the value specified in SecurityContext takes precedence.
     */
    Optional<Boolean> runAsNonRoot();

    /**
     * The UID to run the entrypoint of the container process. Defaults to user specified in image metadata if
     * unspecified. May also be set in PodSecurityContext. If set in both SecurityContext and PodSecurityContext, the
     * value specified in SecurityContext takes precedence.
     */
    Optional<Integer> runAsUser();

    // TODO: seLinuxOptions
    // TODO: windowsOptions

}
