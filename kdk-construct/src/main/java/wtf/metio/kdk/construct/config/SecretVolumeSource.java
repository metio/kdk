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
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#secretvolumesource-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface SecretVolumeSource {

    //region Builders
    static ImmutableSecretVolumeSource.Builder builder() {
        return ImmutableSecretVolumeSource.builder();
    }

    static SecretVolumeSource secretVolumeSource(final String secretName) {
        return ImmutableSecretVolumeSource.of(secretName);
    }
    //endregion

    /**
     * Optional: mode bits to use on created files by default. Must be a value between 0 and 0777. Defaults to 0644.
     * Directories within the path are not affected by this setting. This might be in conflict with other options that
     * affect the file mode, like fsGroup, and the result can be other mode bits set.
     */
    Optional<Integer> defaultMode();

    /**
     * If unspecified, each key-value pair in the Data field of the referenced Secret will be projected into the volume
     * as a file whose name is the key and content is the value. If specified, the listed keys will be projected into
     * the specified paths, and unlisted keys will not be present. If a key is specified which is not present in the
     * Secret, the volume setup will error unless it is marked optional. Paths must be relative and may not contain the
     * '..' path or start with '..'.
     */
    List<KeyToPath> items();

    /**
     * Specify whether the Secret or its keys must be defined
     */
    Optional<Boolean> optional();

    /**
     * Name of the secret in the pod's namespace to use.
     *
     * @see <a href="https://kubernetes.io/docs/concepts/storage/volumes#secret">k8s docs</a>
     */
    @Value.Parameter
    String secretName();

}
