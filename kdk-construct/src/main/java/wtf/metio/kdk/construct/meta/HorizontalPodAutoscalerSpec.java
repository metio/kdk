/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#horizontalpodautoscalerspec-v1-autoscaling">k8s spec</a>
 */
@Value.Immutable
public interface HorizontalPodAutoscalerSpec {

    //region Builders
    static ImmutableHorizontalPodAutoscalerSpec.Builder builder() {
        return ImmutableHorizontalPodAutoscalerSpec.builder();
    }
    //endregion

    /**
     * upper limit for the number of pods that can be set by the autoscaler; cannot be smaller than MinReplicas.
     */
    int maxReplicas();

    /**
     * minReplicas is the lower limit for the number of replicas to which the autoscaler can scale down. It defaults to
     * 1 pod. minReplicas is allowed to be 0 if the alpha feature gate HPAScaleToZero is enabled and at least one Object
     * or External metric is configured. Scaling is active as long as at least one metric value is available.
     */
    Optional<Integer> minReplicas();

    // TODO: Optional<CrossVersionObjectReference> scaleTargetRef();

    /**
     * target average CPU utilization (represented as a percentage of requested CPU) over all the pods; if not specified
     * the default autoscaling policy will be used.
     */
    Optional<Integer> targetCPUUtilizationPercentage();

}
