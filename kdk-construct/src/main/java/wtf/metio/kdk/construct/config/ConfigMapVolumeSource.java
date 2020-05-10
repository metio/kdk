/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.immutables.value.Value;
import wtf.metio.kdk.construct.meta.KeyToPath;

import java.util.List;
import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#volume-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface ConfigMapVolumeSource {

    //region Builders
    static ImmutableConfigMapVolumeSource.Builder builder() {
        return ImmutableConfigMapVolumeSource.builder();
    }

    static ConfigMapVolumeSource configMapVolumeSource(final String name) {
        return ImmutableConfigMapVolumeSource.of(name);
    }
    //endregion

    /**
     * Optional: mode bits to use on created files by default. Must be a value between 0 and 0777. Defaults to 0644.
     * Directories within the path are not affected by this setting. This might be in conflict with other options that
     * affect the file mode, like fsGroup, and the result can be other mode bits set.
     */
    Optional<Integer> defaultMode();

    /**
     * If unspecified, each key-value pair in the Data field of the referenced ConfigMap will be projected into the
     * volume as a file whose name is the key and content is the value. If specified, the listed keys will be projected
     * into the specified paths, and unlisted keys will not be present. If a key is specified which is not present in
     * the ConfigMap, the volume setup will error unless it is marked optional. Paths must be relative and may not
     * contain the '..' path or start with '..'.
     */
    List<KeyToPath> items();

    /**
     * Name of the referent.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#names">k8s docs</a>
     */
    @Value.Parameter
    String name();

    /**
     * Specify whether the ConfigMap or its keys must be defined
     */
    Optional<Boolean> optional();

}
