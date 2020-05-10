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
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#persistentvolumeclaim-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface PersistentVolumeClaim extends HasMetaData {

    //region Builders
    static ImmutablePersistentVolumeClaim.Builder builder() {
        return ImmutablePersistentVolumeClaim.builder();
    }
    //endregion

    /**
     * Spec defines the desired characteristics of a volume requested by a pod author.
     * 
     * @see <a href="https://kubernetes.io/docs/concepts/storage/persistent-volumes#persistentvolumeclaims">k8s docs</a>
     */
    PersistentVolumeClaimSpec spec();

}
