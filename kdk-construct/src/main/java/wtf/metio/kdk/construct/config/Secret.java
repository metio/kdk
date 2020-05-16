/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.internal.HasMetaData;
import wtf.metio.kdk.construct.internal.TopLevelResource;

import java.util.Map;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#secret-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface Secret extends HasMetaData, TopLevelResource {

    //region Builders
    static ImmutableSecret.Builder builder() {
        return ImmutableSecret.builder();
    }
    //endregion

    /**
     * Data contains the secret data. Each key must consist of alphanumeric characters, '-', '_' or '.'. The serialized
     * form of the secret data is a base64 encoded string, representing the arbitrary (possibly non-string) data value
     * here. Described in https://tools.ietf.org/html/rfc4648#section-4
     */
    Map<String, String> data();

    /**
     * stringData allows specifying non-binary secret data in string form. It is provided as a write-only convenience
     * method. All keys and values are merged into the data field on write, overwriting any existing values. It is never
     * output when reading from the API.
     */
    Map<String, String> stringData();

    /**
     * Used to facilitate programmatic handling of secret data.
     */
    String type();

}
