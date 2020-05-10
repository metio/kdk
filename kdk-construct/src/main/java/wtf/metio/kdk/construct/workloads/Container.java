/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.meta.ResourceRequirements;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#container-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface Container {

    //region Builders
    static ImmutableContainer.Builder builder() {
        return ImmutableContainer.builder();
    }
    //endregion

    /**
     * Arguments to the entrypoint. The docker image's CMD is used if this is not provided. Variable references
     * $(VAR_NAME) are expanded using the container's environment. If a variable cannot be resolved, the reference in
     * the input string will be unchanged. The $(VAR_NAME) syntax can be escaped with a double $$, ie: $$(VAR_NAME).
     * Escaped references will never be expanded, regardless of whether the variable exists or not. Cannot be updated.
     * 
     * @see <a href="https://kubernetes.io/docs/tasks/inject-data-application/define-command-argument-container/#running-a-command-in-a-shell">k8s docs</a>
     */
    List<String> args();

    /**
     * Entrypoint array. Not executed within a shell. The docker image's ENTRYPOINT is used if this is not provided.
     * Variable references $(VAR_NAME) are expanded using the container's environment. If a variable cannot be resolved,
     * the reference in the input string will be unchanged. The $(VAR_NAME) syntax can be escaped with a double $$, ie:
     * $$(VAR_NAME). Escaped references will never be expanded, regardless of whether the variable exists or not. Cannot
     * be updated.
     * 
     * @see <a href="https://kubernetes.io/docs/tasks/inject-data-application/define-command-argument-container/#running-a-command-in-a-shell">k8s docs</a>
     */
    List<String> command();

    /**
     * List of environment variables to set in the container. Cannot be updated.
     */
    List<EnvVar> env();

    // TODO: envFrom

    /**
     * Docker image name. More info: https://kubernetes.io/docs/concepts/containers/images This field is optional to
     * allow higher level config management to default or override container images in workload controllers like
     * Deployments and StatefulSets.
     */
    Optional<String> image();

    /**
     * Image pull policy. One of Always, Never, IfNotPresent. Defaults to Always if :latest tag is specified, or
     * IfNotPresent otherwise. Cannot be updated.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/containers/images#updating-images">k8s docs</a>
     */
    Optional<String> imagePullPolicy();

    // TODO: lifecycle

    /**
     * 	Periodic probe of container liveness. Container will be restarted if the probe fails. Cannot be updated.
     * 	
     * 	@see <a href="https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle#container-probes">k8s docs</a>
     */
    Optional<Probe> livenessProbe();

    /**
     * Name of the container specified as a DNS_LABEL. Each container in a pod must have a unique name (DNS_LABEL).
     * Cannot be updated.
     */
    String name();

    // TODO: ports

    /**
     * Periodic probe of container service readiness. Container will be removed from service endpoints if the probe
     * fails. Cannot be updated.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle#container-probes">k8s docs</a>
     */
    Optional<Probe> readinessProbe();

    /**
     * Compute Resources required by this container. Cannot be updated.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/configuration/manage-compute-resources-container/">k8s docs</a>
     */
    Optional<ResourceRequirements> resources();

    /**
     * Security options the pod should run with.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/policy/security-context/">k8s docs</a>
     * @see <a href="https://kubernetes.io/docs/tasks/configure-pod-container/security-context/">k8s docs</a>
     */
    Optional<SecurityContext> securityContext();

    /**
     * StartupProbe indicates that the Pod has successfully initialized. If specified, no other probes are executed
     * until this completes successfully. If this probe fails, the Pod will be restarted, just as if the livenessProbe
     * failed. This can be used to provide different probe parameters at the beginning of a Pod's lifecycle, when it
     * might take a long time to load data or warm a cache, than during steady-state operation. This cannot be updated.
     * This is an alpha feature enabled by the StartupProbe feature flag.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle#container-probes">k8s docs</a>
     */
    Optional<Probe> startupProbe();

    /**
     * Whether this container should allocate a buffer for stdin in the container runtime. If this is not set, reads
     * from stdin in the container will always result in EOF. Default is false.
     */
    Optional<Boolean> stdin();

    /**
     * Whether the container runtime should close the stdin channel after it has been opened by a single attach. When
     * stdin is true the stdin stream will remain open across multiple attach sessions. If stdinOnce is set to true,
     * stdin is opened on container start, is empty until the first client attaches to stdin, and then remains open and
     * accepts data until the client disconnects, at which time stdin is closed and remains closed until the container
     * is restarted. If this flag is false, a container processes that reads from stdin will never receive an EOF.
     * Default is false
     */
    Optional<Boolean> stdinOnce();

    /**
     * Optional: Path at which the file to which the container's termination message will be written is mounted into
     * the container's filesystem. Message written is intended to be brief final status, such as an assertion failure
     * message. Will be truncated by the node if greater than 4096 bytes. The total message length across all containers
     * will be limited to 12kb. Defaults to /dev/termination-log. Cannot be updated.
     */
    Optional<String> terminationMessagePath();

    /**
     * Indicate how the termination message should be populated. File will use the contents of terminationMessagePath
     * to populate the container status message on both success and failure. FallbackToLogsOnError will use the last
     * chunk of container log output if the termination message file is empty and the container exited with an error.
     * The log output is limited to 2048 bytes or 80 lines, whichever is smaller. Defaults to File. Cannot be updated.
     */
    Optional<String> terminationMessagePolicy();

    /**
     * Whether this container should allocate a TTY for itself, also requires 'stdin' to be true. Default is false.
     */
    Optional<Boolean> tty();

    // TODO: volumeDevices

    /**
     * Pod volumes to mount into the container's filesystem. Cannot be updated.
     */
    List<VolumeMount> volumeMounts();

    /**
     * Container's working directory. If not specified, the container runtime's default will be used, which might be
     * configured in the container image. Cannot be updated.
     */
    Optional<String> workingDir();

}
