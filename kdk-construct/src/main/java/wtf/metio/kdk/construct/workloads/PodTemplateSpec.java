/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.meta.ObjectMeta;

import java.util.Optional;

@Value.Immutable
public interface PodTemplateSpec {

    //region Builders
    static ImmutablePodTemplateSpec.Builder builder() {
        return ImmutablePodTemplateSpec.builder();
    }

    static PodTemplateSpec of(final ObjectMeta objectMeta, final PodSpec spec) {
        return ImmutablePodTemplateSpec.builder()
                .metadata(objectMeta)
                .spec(spec)
                .build();
    }
    //endregion

    Optional<ObjectMeta> metadata();

    PodSpec spec();

}
