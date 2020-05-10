/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.yaml.snakeyaml.DumperOptions;
import wtf.metio.kdk.stack.Stack;
import wtf.metio.kdk.tests.Deployments;
import wtf.metio.kdk.tests.Ingresses;
import wtf.metio.kdk.tests.Secrets;
import wtf.metio.kdk.tests.Services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SerializeTest {

    @Test
    void shouldSerializeDeployment() {
        // given
        final var deployment = Deployments.testDeployment();

        // when
        final var yaml = Serialize.asYaml(deployment);

        // then
        assertNotNull(yaml);
    }

    @ParameterizedTest(name = "{1}")
    @EnumSource(K8sVersions.class)
    void shouldSerializeDeploymentWithVersion(final K8sVersions version) {
        // given
        final var deployment = Deployments.testDeployment();

        // when
        final var yaml = Serialize.asYaml(deployment, version);

        // then
        assertNotNull(yaml);
    }

    @Test
    void shouldSerializeService() {
        // given
        final var service = Services.testService();

        // when
        final var yaml = Serialize.asYaml(service);

        // then
        assertNotNull(yaml);
    }

    @ParameterizedTest(name = "{1}")
    @EnumSource(K8sVersions.class)
    void shouldSerializeServiceWithVersion(final K8sVersions version) {
        // given
        final var service = Services.testService();

        // when
        final var yaml = Serialize.asYaml(service, version);

        // then
        assertNotNull(yaml);
    }

    @Test
    void shouldSerializeIngress() {
        // given
        final var ingress = Ingresses.testIngress();

        // when
        final var yaml = Serialize.asYaml(ingress);

        // then
        assertNotNull(yaml);
    }

    @ParameterizedTest(name = "{1}")
    @EnumSource(K8sVersions.class)
    void shouldSerializeIngressWithVersion(final K8sVersions version) {
        // given
        final var ingress = Ingresses.testIngress();

        // when
        final var yaml = Serialize.asYaml(ingress, version);

        // then
        assertNotNull(yaml);
    }

    @Test
    void shouldSerializeSecret() {
        // given
        final var secret = Secrets.dockerconfigjson();

        // when
        final var yaml = Serialize.asYaml(secret);

        // then
        assertNotNull(yaml);
    }

    @ParameterizedTest(name = "{1}")
    @EnumSource(K8sVersions.class)
    void shouldSerializeSecretWithVersion(final K8sVersions version) {
        // given
        final var secret = Secrets.dockerconfigjson();

        // when
        final var yaml = Serialize.asYaml(secret, version);

        // then
        assertNotNull(yaml);
    }

    @Test
    void shouldSerializeStack() {
        // given
        final var stack = Stack.builder().build();

        // when
        final var yaml = Serialize.asYaml(stack);

        // then
        assertNotNull(yaml);
    }

    @ParameterizedTest(name = "{1}")
    @EnumSource(K8sVersions.class)
    void shouldSerializeStackWithVersion(final K8sVersions version) {
        // given
        final var stack = Stack.builder().build();

        // when
        final var yaml = Serialize.asYaml(stack, version);

        // then
        assertNotNull(yaml);
    }

    @ParameterizedTest(name = "{1}")
    @EnumSource(K8sVersions.class)
    void shouldSerializeStackWithVersionAndCustomFormat(final K8sVersions version) {
        // given
        final var options = new DumperOptions();
        options.setExplicitStart(true);
        options.setIndent(2);
        final var stack = Stack.builder().build();

        // when
        final var yaml = Serialize.asYaml(stack, version, options);

        // then
        assertNotNull(yaml);
    }

}
