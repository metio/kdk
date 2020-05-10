/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import org.junit.jupiter.api.Test;
import wtf.metio.kdk.tests.Stacks;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static wtf.metio.kdk.construct.workloads.ServicePort.of;
import static wtf.metio.kdk.stack.Stack.stack;
import static wtf.metio.kdk.tests.Deployments.createDeployment;
import static wtf.metio.kdk.tests.Ingresses.createIngress;
import static wtf.metio.kdk.tests.Services.createService;
import static wtf.metio.kdk.validate.Validate.validate;

class ValidateTest {

    @Test
    void shouldValidateStack() {
        assertTrue(validate(Stacks.publicServiceDeployment()).isEmpty());
    }

    @Test
    void shouldValidateDeployment() {
        // given
        final var servicePort = of(8080);

        // when
        final var errors = validate(createDeployment(servicePort));

        // then
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldValidateService() {
        // given
        final var servicePort = of(8080);

        // when
        final var errors = validate(createService(servicePort));

        // then
        assertTrue(errors.isEmpty());
    }

    @Test
    void shouldValidateIngress() {
        // given
        final var servicePort = of(8080);

        // when
        final var errors = validate(createIngress(servicePort));

        // then
        assertTrue(errors.isEmpty());
    }

}
