/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.meta.Annotation;
import wtf.metio.kdk.construct.meta.ObjectMeta;
import wtf.metio.kdk.construct.service.*;
import wtf.metio.kdk.construct.workloads.ServicePort;

import static wtf.metio.kdk.construct.meta.Annotation.certManagerClusterIssuer;
import static wtf.metio.kdk.construct.meta.Annotation.k8sTlsAcme;
import static wtf.metio.kdk.tests.ObjectMetas.testMetadata;

public final class Ingresses {

    public static Ingress testIngress() {
        return createIngress(ServicePort.of(8080));
    }

    public static Ingress createIngress(final ServicePort servicePort) {
        final var metadata = testMetadata();
        final IngressSpec ingressSpec = IngressSpecs.createIngressSpec(servicePort);
        final var annotations = metadata.withAnnotations(k8sTlsAcme(true), certManagerClusterIssuer("test-tls"));
        return Ingress.of(annotations, ingressSpec);
    }

    public static Ingress createConanIngress() {
        return Ingress.of(
                ObjectMeta.builder()
                        .name("test")
                        .namespace("example")
                        .addAnnotations(Annotation.of("kubernetes.io/ingress.class", "nginx"))
                        .addAnnotations(Annotation.of("nginx.ingress.kubernetes.io/ssl-passthrough", "true"))
                        .build(),
                IngressSpec.builder()
                        .addRules(IngressRule.of("example.tld", HTTPIngressRuleValue.builder()
                                .addPaths(HTTPIngressPath.of(
                                        IngressBackend.of("test", ServicePort.of(9194)),
                                        "/actuator/health"))
                                .addPaths(HTTPIngressPath.of(
                                        IngressBackend.of("test", ServicePort.of(2002)),
                                        "/service"))
                                .build()))
                        .addTls(IngressTLS.of("tls-secret", "example.tld"))
                        .build());
    }

    private Ingresses() {
        // factory class
    }

}
