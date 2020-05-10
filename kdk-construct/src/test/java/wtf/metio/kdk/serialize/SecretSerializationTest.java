/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.junit.jupiter.params.provider.Arguments;
import wtf.metio.kdk.construct.config.Secret;
import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.construct.service.IngressRule;
import wtf.metio.kdk.construct.service.IngressSpec;
import wtf.metio.kdk.construct.service.IngressTLS;
import wtf.metio.kdk.construct.workloads.ServicePort;
import wtf.metio.kdk.serialize.SerializationTCK;
import wtf.metio.kdk.stack.Stack;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class SecretSerializationTest implements SerializationTCK {

    @Override
    public String basePath() {
        return "src/test/resources/wtf/metio/kdk/serialize/config/SecretTest/";
    }

    static Stream<Arguments> yamlAndStack() {
        return Stream.of(
                arguments("simpleSecret.yaml", asStack(simpleSecret()))
        );
    }

    private static Secret simpleSecret() {
        return Secret.builder()
                .type("test")
                .build();
    }

    private static Stack asStack(final Secret secret) {
        return Stack.builder()
                .addSecrets(secret)
                .build();
    }

}
