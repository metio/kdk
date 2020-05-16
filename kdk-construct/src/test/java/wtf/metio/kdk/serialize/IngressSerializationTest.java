/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.junit.jupiter.params.provider.Arguments;
import wtf.metio.kdk.construct.service.Ingress;
import wtf.metio.kdk.construct.service.IngressRule;
import wtf.metio.kdk.construct.service.IngressSpec;
import wtf.metio.kdk.construct.service.IngressTLS;
import wtf.metio.kdk.construct.workloads.ServicePort;
import wtf.metio.kdk.serialize.SerializationTCK;
import wtf.metio.kdk.stack.ImmutableStack;
import wtf.metio.kdk.stack.Stack;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class IngressSerializationTest implements SerializationTCK {

    @Override
    public String basePath() {
        return "src/test/resources/wtf/metio/kdk/serialize/IngressTest/";
    }

    static Stream<Arguments> yamlAndStack() {
        return Stream.of(
                arguments("simpleIngress.yaml", asStack(simpleIngress())),
                arguments("ingressWithSpec.yaml", asStack(ingressWithSpec())),
                arguments("tlsIngressWithSpec.yaml", asStack(tlsIngressWithSpec())),
                arguments("ingressWithMultiplePaths.yaml", asStack(ingressWithMultiplePaths()))
        );
    }

    private static Ingress simpleIngress() {
        return Ingress.builder()
                .spec(IngressSpec.builder().build())
                .build();
    }

    private static Ingress ingressWithSpec() {
        return Ingress.builder()
                .spec(IngressSpec.builder()
                        .addRules(IngressRule.of("example.com", "test", ServicePort.of(8080)))
                        .build())
                .build();
    }

    private static Ingress tlsIngressWithSpec() {
        return Ingress.builder()
                .spec(IngressSpec.builder()
                        .addRules(IngressRule.of("example.com", "test", ServicePort.of(8080)))
                        .addTls(IngressTLS.of("tls-secret", "example.com"))
                        .build())
                .build();
    }

    private static Ingress ingressWithMultiplePaths() {
        return Ingress.builder()
                .spec(IngressSpec.builder()
                        .addRules(IngressRule.of("example.com", "test", ServicePort.of(8080)))
                        .addRules(IngressRule.of("www.example.com", "test", ServicePort.of(8080)))
                        .build())
                .build();
    }

    private static Stack asStack(final Ingress ingress) {
        return Stack.builder()
                .addIngress(ingress)
                .build();
    }

}
