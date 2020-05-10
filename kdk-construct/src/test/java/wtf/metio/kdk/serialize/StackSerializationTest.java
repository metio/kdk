/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.junit.jupiter.params.provider.Arguments;
import wtf.metio.kdk.construct.config.Secret;
import wtf.metio.kdk.serialize.SerializationTCK;
import wtf.metio.kdk.stack.Stack;
import wtf.metio.kdk.tests.*;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static wtf.metio.kdk.construct.workloads.ServicePort.of;
import static wtf.metio.kdk.stack.Stack.stack;
import static wtf.metio.kdk.tests.Deployments.createDeployment;
import static wtf.metio.kdk.tests.Ingresses.createIngress;
import static wtf.metio.kdk.tests.Services.createService;

class StackSerializationTest implements SerializationTCK {

    @Override
    public String basePath() {
        return "src/test/resources/wtf/metio/kdk/serialize/stack/StackTest/";
    }

    static Stream<Arguments> yamlAndStack() {
        return Stream.of(
                arguments("simpleStack.yaml", Stacks.simpleStack()),
                arguments("publicServiceDeployment.yaml", Stacks.publicServiceDeployment()),
                arguments("conan.yaml", Stacks.conan())
        );
    }

}
