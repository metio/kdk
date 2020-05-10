/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

import java.util.Optional;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#keytopath-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface KeyToPath {

    //region Builders
    static ImmutableKeyToPath.Builder builder() {
        return ImmutableKeyToPath.builder();
    }

    static KeyToPath keyToPath(final String key, final String path) {
        return ImmutableKeyToPath.of(key, path);
    }
    //endregion

    /**
     * The key to project.
     */
    @Value.Parameter
    String key();

    /**
     * Optional: mode bits to use on this file, must be a value between 0 and 0777. If not specified, the volume
     * defaultMode will be used. This might be in conflict with other options that affect the file mode, like fsGroup,
     * and the result can be other mode bits set.
     */
    Optional<Integer> mode();

    /**
     * The relative path of the file to map the key to. May not be an absolute path. May not contain the path element
     * '..'. May not start with the string '..'.
     */
    @Value.Parameter
    String path();

}
