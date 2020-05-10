/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.meta.LabelSelector;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#deploymentspec-v1-apps">k8s spec</a>
 */
@Value.Immutable
public interface DeploymentSpec {

    //region Builders
    static ImmutableDeploymentSpec.Builder builder() {
        return ImmutableDeploymentSpec.builder();
    }
    //endregion

    /**
     * Minimum number of seconds for which a newly created pod should be ready without any of its container crashing,
     * for it to be considered available. Defaults to 0 (pod will be considered available as soon as it is ready)
     */
    Optional<Integer> minReadySeconds();

    /**
     * Indicates that the deployment is paused.
     */
    Optional<Boolean> paused();

    /**
     * The maximum time in seconds for a deployment to make progress before it is considered to be failed. The
     * deployment controller will continue to process failed deployments and a condition with a ProgressDeadlineExceeded
     * reason will be surfaced in the deployment status. Note that progress will not be estimated during the time a
     * deployment is paused. Defaults to 600s.
     */
    Optional<Integer> progressDeadlineSeconds();

    /**
     * Number of desired pods. This is a pointer to distinguish between explicit zero and not specified. Defaults to 1.
     */
    Optional<Integer> replicas();

    /**
     * The number of old ReplicaSets to retain to allow rollback. This is a pointer to distinguish between explicit zero
     * and not specified. Defaults to 10.
     */
    Optional<Integer> revisionHistoryLimit();

    /**
     * Label selector for pods. Existing ReplicaSets whose pods are selected by this will be the ones affected by this
     * deployment. It must match the pod template's labels.
     */
    LabelSelector selector();

    /**
     * The deployment strategy to use to replace existing pods with new ones.
     */
    Optional<DeploymentStrategy> strategy();

    /**
     * Template describes the pods that will be created.
     */
    PodTemplateSpec template();

}
