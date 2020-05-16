/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.workloads;

import org.junit.jupiter.api.Test;
import wtf.metio.kdk.construct.ConstructTCK;
import wtf.metio.kdk.construct.ConstructWithBuilderTCK;
import wtf.metio.kdk.tests.DeploymentSpecs;
import wtf.metio.kdk.tests.Deployments;
import wtf.metio.kdk.tests.ObjectMetas;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DeploymentTest implements ConstructTCK<Deployment>, ConstructWithBuilderTCK<ImmutableDeployment.Builder> {

    static Stream<Deployment> types() {
        return Stream.of(
                Deployments.testDeployment(),
                Deployments.createConanDeployment(),
                Deployments.createDeployment(ServicePort.of(12345))
        );
    }

    @Override
    public ImmutableDeployment.Builder builder() {
        return Deployment.builder();
    }

    @Test
    void shouldSupportFactoryMethod() {
        // given
        final var metadata = ObjectMetas.testMetadata();
        final var spec = DeploymentSpecs.testDeploymentSpec();

        // when
        final var service = Deployment.of(metadata, spec);

        // then
        assertNotNull(service);
    }

}
