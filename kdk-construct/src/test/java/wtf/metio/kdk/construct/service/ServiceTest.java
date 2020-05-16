/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import org.junit.jupiter.api.Test;
import wtf.metio.kdk.construct.ConstructTCK;
import wtf.metio.kdk.construct.ConstructWithBuilderTCK;
import wtf.metio.kdk.construct.workloads.ServicePort;
import wtf.metio.kdk.tests.ObjectMetas;
import wtf.metio.kdk.tests.ServiceSpecs;
import wtf.metio.kdk.tests.Services;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceTest implements ConstructTCK<Service>, ConstructWithBuilderTCK<ImmutableService.Builder> {

    static Stream<Service> types() {
        return Stream.of(
                Services.testService(),
                Services.createConanService(),
                Services.createService(ServicePort.of(12345))
        );
    }

    @Override
    public ImmutableService.Builder builder() {
        return Service.builder();
    }

    @Test
    void shouldSupportFactoryMethod() {
        // given
        final var metadata = ObjectMetas.testMetadata();
        final var spec = ServiceSpecs.testServiceSpec();

        // when
        final var service = Service.of(metadata, spec);

        // then
        assertNotNull(service);
    }

}
