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
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#rollingupdatedeployment-v1-apps">k8s spec</a>
 */
@Value.Immutable
public interface RollingUpdateDeployment {

    static ImmutableRollingUpdateDeployment.Builder builder() {
        return ImmutableRollingUpdateDeployment.builder();
    }

    /**
     * The maximum number of pods that can be scheduled above the desired number of pods. Value can be an absolute
     * number (ex: 5) or a percentage of desired pods (ex: 10%). This can not be 0 if MaxUnavailable is 0. Absolute
     * number is calculated from percentage by rounding up. Defaults to 25%. Example: when this is set to 30%, the new
     * ReplicaSet can be scaled up immediately when the rolling update starts, such that the total number of old and new
     * pods do not exceed 130% of desired pods. Once old pods have been killed, new ReplicaSet can be scaled up further,
     * ensuring that total number of pods running at any time during the update is at most 130% of desired pods.
     *
     * @see #maxSurgePercent()
     */
    Optional<Integer> maxSurge();

    /**
     * The maximum number of pods that can be scheduled above the desired number of pods. Value can be an absolute
     * number (ex: 5) or a percentage of desired pods (ex: 10%). This can not be 0 if MaxUnavailable is 0. Absolute
     * number is calculated from percentage by rounding up. Defaults to 25%. Example: when this is set to 30%, the new
     * ReplicaSet can be scaled up immediately when the rolling update starts, such that the total number of old and new
     * pods do not exceed 130% of desired pods. Once old pods have been killed, new ReplicaSet can be scaled up further,
     * ensuring that total number of pods running at any time during the update is at most 130% of desired pods.
     *
     * @see #maxSurge()
     */
    Optional<String> maxSurgePercent();

    /**
     * The maximum number of pods that can be unavailable during the update. Value can be an absolute number (ex: 5) or
     * a percentage of desired pods (ex: 10%). Absolute number is calculated from percentage by rounding down. This can
     * not be 0 if MaxSurge is 0. Defaults to 25%. Example: when this is set to 30%, the old ReplicaSet can be scaled
     * down to 70% of desired pods immediately when the rolling update starts. Once new pods are ready, old ReplicaSet
     * can be scaled down further, followed by scaling up the new ReplicaSet, ensuring that the total number of pods
     * available at all times during the update is at least 70% of desired pods.
     *
     * @see #maxUnavailablePercent()
     */
    Optional<Integer> maxUnavailable();

    /**
     * The maximum number of pods that can be unavailable during the update. Value can be an absolute number (ex: 5) or
     * a percentage of desired pods (ex: 10%). Absolute number is calculated from percentage by rounding down. This can
     * not be 0 if MaxSurge is 0. Defaults to 25%. Example: when this is set to 30%, the old ReplicaSet can be scaled
     * down to 70% of desired pods immediately when the rolling update starts. Once new pods are ready, old ReplicaSet
     * can be scaled down further, followed by scaling up the new ReplicaSet, ensuring that the total number of pods
     * available at all times during the update is at least 70% of desired pods.
     *
     * @see #maxUnavailable()
     */
    Optional<String> maxUnavailablePercent();

}
