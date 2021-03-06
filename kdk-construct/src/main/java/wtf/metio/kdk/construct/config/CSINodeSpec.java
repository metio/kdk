/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;

import java.util.List;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#csinodespec-v1-storage-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface CSINodeSpec {

    //region Builders
    static ImmutableCSINodeSpec.Builder builder() {
        return ImmutableCSINodeSpec.builder();
    }
    //endregion

    /**
     * drivers is a list of information of all CSI Drivers existing on a node. If all drivers in the list are
     * uninstalled, this can become empty.
     */
    List<CSINodeDriver> drivers();

}
