/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.internal.HasMetaData;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#volume-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface Job extends HasMetaData {

    //region Builders
    static ImmutableJob.Builder builder() {
        return ImmutableJob.builder();
    }
    //endregion

    /**
     * Specification of the desired behavior of a job.
     * 
     * @see <a href="https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status">k8s docs</a>
     */
    JobSpec spec();

    /**
     * Current status of a job.
     * 
     * @see <a href="https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#spec-and-status">k8s docs</a>
     */
    JobStatus status();

}
