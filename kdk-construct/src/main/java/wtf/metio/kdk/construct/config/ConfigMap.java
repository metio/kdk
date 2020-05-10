/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.internal.HasMetaData;

import java.util.Map;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#configmap-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface ConfigMap extends HasMetaData {

    //region Builders
    static ImmutableConfigMap.Builder builder() {
        return ImmutableConfigMap.builder();
    }
    //endregion

    /**
     * BinaryData contains the binary data. Each key must consist of alphanumeric characters, '-', '_' or '.'.
     * BinaryData can contain byte sequences that are not in the UTF-8 range. The keys stored in BinaryData must not
     * overlap with the ones in the Data field, this is enforced during validation process. Using this field will
     * require 1.10+ apiserver and kubelet.
     */
    Map<String, String> binaryData();

    /**
     * Data contains the configuration data. Each key must consist of alphanumeric characters, '-', '_' or '.'. Values
     * with non-UTF-8 byte sequences must use the BinaryData field. The keys stored in Data must not overlap with the
     * keys in the BinaryData field, this is enforced during validation process.
     */
    Map<String, String> data();

}
