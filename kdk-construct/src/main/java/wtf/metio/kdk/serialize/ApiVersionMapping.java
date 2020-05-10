/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.construct.workloads.Deployment;

import java.util.Map;

import static wtf.metio.kdk.serialize.K8sVersions.VERSION_1_16;

final class ApiVersionMapping {

    private static final Map<Class<?>, String> version_1_16 = Map.ofEntries(
            Map.entry(Deployment.class, "apps/v1"),
            Map.entry(Ingress.class, "networking.k8s.io/v1beta1")
    );
    private static final Map<K8sVersions, Map<Class<?>, String>> VERSIONS_MAP = Map.of(VERSION_1_16, version_1_16);

    public static String version(final Class<?> clazz, final K8sVersions version) {
        return VERSIONS_MAP.getOrDefault(version, version_1_16).getOrDefault(clazz, "v1");
    }

    private ApiVersionMapping() {
        // factory class
    }

}
