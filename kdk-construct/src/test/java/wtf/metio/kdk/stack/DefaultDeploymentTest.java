/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.stack;

import org.junit.jupiter.api.Test;
import wtf.metio.kdk.construct.meta.Annotation;
import wtf.metio.kdk.construct.meta.Label;
import wtf.metio.kdk.construct.meta.LabelSelector;
import wtf.metio.kdk.construct.meta.ResourceConstraint;
import wtf.metio.kdk.construct.workloads.EnvVar;
import wtf.metio.kdk.construct.workloads.HTTPGetAction;
import wtf.metio.kdk.construct.workloads.Probe;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultDeploymentTest {

    @Test
    void shouldCreateDefaultDeployment() {
        // given
        final var port = 9194;
        final var probePath = "/actuator/health";
        final var label = Label.of("app", "example");

        // when
        final var deployment = DefaultDeployment.builder()
                .name("my-awesome-service")
                .image("example/service:1.2.3")
                .addEnv(EnvVar.of("key", "value"))
                .addLabels(label)
                .addAnnotations(Annotation.of("key", "value"))
                .replicas(3)
                .startupProbe(Probe.builder()
                        .failureThreshold(10)
                        .periodSeconds(2)
                        .httpGet(HTTPGetAction.of(port, probePath))
                        .build())
                .readinessProbe(Probe.builder()
                        .failureThreshold(3)
                        .periodSeconds(5)
                        .httpGet(HTTPGetAction.of(port, probePath))
                        .build())
                .livenessProbe(Probe.builder()
                        .failureThreshold(2)
                        .periodSeconds(15)
                        .httpGet(HTTPGetAction.of(port, probePath))
                        .build())
                .selector(LabelSelector.of(label))
                .resources(ResourceConstraint.of("100m", "128Mi"))
                .build()
                .asDeployment();

        // then
        assertNotNull(deployment);
    }

}
