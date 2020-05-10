/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.meta.LocalObjectReference;
import wtf.metio.kdk.construct.config.Volume;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#podspec-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface PodSpec {

    //region Builders
    static ImmutablePodSpec.Builder builder() {
        return ImmutablePodSpec.builder();
    }
    //endregion

    /**
     * Optional duration in seconds the pod may be active on the node relative to StartTime before the system will
     * actively try to mark it failed and kill associated containers. Value must be a positive integer.
     */
    Optional<Integer> activeDeadlineSeconds();

    // TODO: affinity()

    /**
     * AutomountServiceAccountToken indicates whether a service account token should be automatically mounted.
     */
    Optional<Boolean> automountServiceAccountToken();

    /**
     * List of containers belonging to the pod. Containers cannot currently be added or removed. There must be at least
     * one container in a Pod. Cannot be updated.
     */
    List<Container> containers();

    // TODO: dnsConfig()

    /**
     * Set DNS policy for the pod. Defaults to "ClusterFirst". Valid values are 'ClusterFirstWithHostNet',
     * 'ClusterFirst', 'Default' or 'None'. DNS parameters given in DNSConfig will be merged with the policy selected
     * with DNSPolicy. To have DNS options set along with hostNetwork, you have to specify DNS policy explicitly to
     * 'ClusterFirstWithHostNet'.
     */
    Optional<String> dnsPolicy();

    /**
     * EnableServiceLinks indicates whether information about services should be injected into pod's environment
     * variables, matching the syntax of Docker links. Optional: Defaults to true.
     */
    Optional<Boolean> enableServiceLinks();

    // TODO: ephemeralContainers()
    // TODO: hostAliases()

    /**
     * Use the host's ipc namespace. Optional: Default to false.
     */
    Optional<Boolean> hostIPC();

    /**
     * Host networking requested for this pod. Use the host's network namespace. If this option is set, the ports that
     * will be used must be specified. Default to false.
     */
    Optional<Boolean> hostNetwork();

    /**
     * Use the host's pid namespace. Optional: Default to false.
     */
    Optional<Boolean> hostPID();

    /**
     * Specifies the hostname of the Pod If not specified, the pod's hostname will be set to a system-defined value.
     */
    Optional<Boolean> hostname();

    /**
     * ImagePullSecrets is an optional list of references to secrets in the same namespace to use for pulling any of
     * the images used by this PodSpec. If specified, these secrets will be passed to individual puller implementations
     * for them to use. For example, in the case of docker, only DockerConfig type secrets are honored.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/containers/images#specifying-imagepullsecrets-on-a-pod">k8s docs</a>
     */
    List<LocalObjectReference> imagePullSecrets();

    // TODO: initContainers();

    /**
     * NodeName is a request to schedule this pod onto a specific node. If it is non-empty, the scheduler simply
     * schedules this pod onto that node, assuming that it fits resource requirements.
     */
    Optional<String> nodeName();

    // TODO: nodeSelector()
    // TODO: overhead()

    /**
     * PreemptionPolicy is the Policy for preempting pods with lower priority. One of Never, PreemptLowerPriority.
     * Defaults to PreemptLowerPriority if unset. This field is alpha-level and is only honored by servers that enable
     * the NonPreemptingPriority feature.
     */
    Optional<String> preemptionPolicy();

    /**
     * The priority value. Various system components use this field to find the priority of the pod. When Priority
     * Admission Controller is enabled, it prevents users from setting this field. The admission controller populates
     * this field from PriorityClassName. The higher the value, the higher the priority.
     */
    Optional<Integer> priority();

    /**
     * If specified, indicates the pod's priority. "system-node-critical" and "system-cluster-critical" are two special
     * keywords which indicate the highest priorities with the former being the highest priority. Any other name must be
     * defined by creating a PriorityClass object with that name. If not specified, the pod priority will be default or
     * zero if there is no default.
     */
    Optional<String> priorityClassName();

    // TODO: readinessGates()

    /**
     * Restart policy for all containers within the pod. One of Always, OnFailure, Never. Default to Always.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/workloads/pods/pod-lifecycle/#restart-policy">k8s docs</a>
     */
    Optional<String> restartPolicy();

    /**
     * RuntimeClassName refers to a RuntimeClass object in the node.k8s.io group, which should be used to run this pod.
     * If no RuntimeClass resource matches the named class, the pod will not be run. If unset or empty, the "legacy"
     * RuntimeClass will be used, which is an implicit class with an empty definition that uses the default runtime
     * handler. More info: https://git.k8s.io/enhancements/keps/sig-node/runtime-class.md This is a beta feature as of
     * Kubernetes v1.14.
     */
    Optional<String> runtimeClassName();

    /**
     * If specified, the pod will be dispatched by specified scheduler. If not specified, the pod will be dispatched by
     * default scheduler.
     */
    Optional<String> schedulerName();

    /**
     * SecurityContext holds pod-level security attributes and common container settings. Optional: Defaults to empty.
     * See type description for default values of each field.
     */
    Optional<PodSecurityContext> securityContext();

    /**
     * DeprecatedServiceAccount is a depreciated alias for ServiceAccountName. Deprecated: Use
     * {@link #serviceAccountName()} instead.
     */
    @Deprecated
    Optional<String> serviceAccount();

    /**
     * ServiceAccountName is the name of the ServiceAccount to use to run this pod.
     *
     * @see <a href="https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/">k8s docs</a>
     */
    Optional<String> serviceAccountName();

    /**
     * Share a single process namespace between all of the containers in a pod. When this is set containers will be able
     * to view and signal processes from other containers in the same pod, and the first process in each container will
     * not be assigned PID 1. HostPID and ShareProcessNamespace cannot both be set. Optional: Default to false.
     */
    Optional<Boolean> shareProcessNamespace();

    /**
     * If specified, the fully qualified Pod hostname will be
     * "<hostname>.<subdomain>.<pod namespace>.svc.<cluster domain>". If not specified, the pod will not have a
     * domainname at all.
     */
    Optional<String> subdomain();

    /**
     * Optional duration in seconds the pod needs to terminate gracefully. May be decreased in delete request. Value
     * must be non-negative integer. The value zero indicates delete immediately. If this value is nil, the default
     * grace period will be used instead. The grace period is the duration in seconds after the processes running in the
     * pod are sent a termination signal and the time when the processes are forcibly halted with a kill signal. Set
     * this value longer than the expected cleanup time for your process. Defaults to 30 seconds.
     */
    Optional<Integer> terminationGracePeriodSeconds();

    // TODO: tolerations()
    // TODO: topologySpreadConstraints()

    /**
     * List of volumes that can be mounted by containers belonging to the pod.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/storage/volumes">k8s docs</a>
     */
    List<Volume> volumes();

}
