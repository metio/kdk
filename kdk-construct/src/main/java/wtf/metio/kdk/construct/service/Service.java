/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.internal.HasMetaData;
import wtf.metio.kdk.construct.meta.ObjectMeta;

@Value.Immutable
public interface Service extends HasMetaData {

    //region Builders
    static ImmutableService.Builder builder() {
        return ImmutableService.builder();
    }

    static Service service(final ObjectMeta objectMeta, final ServiceSpec spec) {
        return builder()
                .metadata(objectMeta)
                .spec(spec)
                .build();
    }
    //endregion

    ServiceSpec spec();

}
