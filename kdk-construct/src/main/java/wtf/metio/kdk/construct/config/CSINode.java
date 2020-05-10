/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.internal.HasMetaData;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#csinode-v1-storage-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface CSINode extends HasMetaData {

    //region Builders
    static ImmutableCSINode.Builder builder() {
        return ImmutableCSINode.builder();
    }
    //endregion

    /**
     * spec is the specification of CSINode
     */
    CSINodeSpec spec();

}
