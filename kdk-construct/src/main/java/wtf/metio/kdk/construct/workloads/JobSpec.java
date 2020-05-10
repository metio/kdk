/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#jobspec-v1-batch">k8s spec</a>
 */
@Value.Immutable
public interface JobSpec {

    //region Builders
    static ImmutableJobSpec.Builder builder() {
        return ImmutableJobSpec.builder();
    }
    //endregion

}
