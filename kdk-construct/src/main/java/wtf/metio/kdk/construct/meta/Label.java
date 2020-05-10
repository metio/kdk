/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.meta;

import org.immutables.value.Value;

@Value.Immutable(builder = false)
public interface Label {

    //region Builders
    static Label of(final String key, final String value) {
        return ImmutableLabel.of(key, value);
    }

    //region helm
    /**
     * <code>helm.sh/chart: {{name}}</code>
     *
     * @param name The name of the helm chart.
     */
    static Label helmChart(final String name) {
        return of("helm.sh/chart", name);
    }
    //endregion

    //region kubernetes
    /**
     * <code>app.kubernetes.io/name: {{name}}</code>
     *
     * @param name The name of the application
     */
    static Label k8sName(final String name) {
        return of("app.kubernetes.io/name", name);
    }

    /**
     * <code>app.kubernetes.io/version: {{version}}</code>
     *
     * @param version The version of the application
     */
    static Label k8sVersion(final String version) {
        return of("app.kubernetes.io/version", version);
    }

    /**
     * <code>app.kubernetes.io/managed-by: {{managedBy}}</code>
     *
     * @param managedBy The tool being used to manage the operation of an application.
     */
    static Label k8sManagedBy(final String managedBy) {
        return of("app.kubernetes.io/managed-by", managedBy);
    }

    /**
     * <code>app.kubernetes.io/instance: {{instance}}</code>
     *
     * @param instance A unique name identifying the instance of an application.
     */
    static Label k8sInstance(final String instance) {
        return of("app.kubernetes.io/instance", instance);
    }

    /**
     * <code>app.kubernetes.io/component: {{component}}</code>
     *
     * @param component The component within the architecture.
     */
    static Label k8sComponent(final String component) {
        return of("app.kubernetes.io/component", component);
    }

    /**
     * <code>app.kubernetes.io/part-of: {{partOf}}</code>
     *
     * @param partOf The name of a higher level application this one is part of.
     */
    static Label k8sPartOf(final String partOf) {
        return of("app.kubernetes.io/part-of", partOf);
    }
    //endregion
    //endregion

    @Value.Parameter
    String key();

    @Value.Parameter
    String value();

}
