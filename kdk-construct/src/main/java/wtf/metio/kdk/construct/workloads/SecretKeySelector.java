/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.immutables.value.Value;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#secretkeyselector-v1-core">k8s spec</a>
 */
@Value.Immutable
public interface SecretKeySelector {

    //region Builders
    static SecretKeySelector secretKeySelector(final String key, final String name, final boolean optional) {
        return ImmutableSecretKeySelector.of(key, name, optional);
    }
    //endregion

    @Value.Parameter
    String key();

    @Value.Parameter
    String name();

    @Value.Parameter
    boolean optional();

}
