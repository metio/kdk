/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import org.junit.jupiter.params.provider.Arguments;
import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.construct.service.IngressSpec;
import wtf.metio.kdk.construct.workloads.ServicePort;
import wtf.metio.kdk.stack.Stack;
import wtf.metio.kdk.tests.Stacks;
import wtf.metio.kdk.validate.ValidationTCK;
import wtf.metio.kdk.validate.Validators;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static wtf.metio.kdk.tests.Ingresses.createIngress;

class StackValidationTest implements ValidationTCK {

    static Stream<Arguments> validStacks() {
        return Stream.of(
                arguments(Stacks.simpleStack(), Validators.recommended()),
                arguments(Stacks.publicServiceDeployment(), Validators.recommended()),
                arguments(Stack.builder().addIngress(createIngress(ServicePort.of(8080))).build(), Validators.recommended())
        );
    }

    static Stream<Arguments> invalidStacks() {
        return Stream.of(
                arguments(Stacks.conan(), Validators.recommended()),
                arguments(Stack.builder()
                        .addIngress(Ingress.builder().spec(IngressSpec.builder().build()).build())
                        .build(), Validators.recommended()));
    }

}
