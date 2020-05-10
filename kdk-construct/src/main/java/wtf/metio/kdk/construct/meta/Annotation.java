/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

@Value.Immutable
public interface Annotation {

    //region Builders
    static Annotation of(final String key, final String value) {
        return ImmutableAnnotation.of(key, value);
    }

    //region kubernetes

    /**
     * <code>kubernetes.io/tls-acme: {{value}}</code>
     *
     * @param value Enable ACME usage for TLS
     */
    static Annotation k8sTlsAcme(final boolean value) {
        return of("kubernetes.io/tls-acme", Boolean.toString(value));
    }
    //endregion

    //region cert-manager

    /**
     * <code>cert-manager.io/cluster-issuer: {{issuer}}</code>
     *
     * @param issuer The name of the cluster certificate issuer.
     */
    static Annotation certManagerClusterIssuer(final String issuer) {
        return of("cert-manager.io/cluster-issuer", issuer);
    }
    //endregion
    //endregion

    @Value.Parameter
    String key();

    @Value.Parameter
    String value();

}
