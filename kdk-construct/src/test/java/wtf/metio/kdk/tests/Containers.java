/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.meta.ResourceConstraint;
import wtf.metio.kdk.construct.meta.ResourceRequirements;
import wtf.metio.kdk.construct.workloads.*;

import static wtf.metio.kdk.construct.workloads.EnvVar.of;
import static wtf.metio.kdk.construct.workloads.EnvVarSource.envVarSource;
import static wtf.metio.kdk.construct.workloads.SecretKeySelector.secretKeySelector;
import static wtf.metio.kdk.construct.workloads.VolumeMount.volumeMount;

public final class Containers {

    public static Container createContainer(final ServicePort servicePort) {
        final var httpGet = HTTPGetAction.of(servicePort.port(), "/");
        final var probe = Probe.builder()
                .initialDelaySeconds(30)
                .periodSeconds(15)
                .failureThreshold(3)
                .successThreshold(5)
                .timeoutSeconds(2)
                .httpGet(httpGet)
                .build();
        final var constraint = ResourceConstraint.of("100m", "128Mi");
        final var resources = ResourceRequirements.of(constraint, constraint);
        return Container.builder()
                .image("io.docker/orga/awesome-service:latest")
                .name("awesome-service")
                .livenessProbe(probe)
                .readinessProbe(probe)
                .resources(resources)
                .addEnv(EnvVar.of("DATABASE_HOST", "192.168.1.38"))
                .addEnv(of("DATABASE_PASSWORD",
                        envVarSource(secretKeySelector("db-pass", "db-secrets", false))))
                .addVolumeMounts(volumeMount("example", "/opt/data"))
                .build();
    }

    private Containers() {
        // factory class
    }

}
