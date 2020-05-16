/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.service.IngressRule;
import wtf.metio.kdk.construct.service.IngressSpec;
import wtf.metio.kdk.construct.service.IngressTLS;
import wtf.metio.kdk.construct.workloads.ServicePort;

public final class IngressSpecs {

    public static IngressSpec testIngressSpec() {
        return createIngressSpec(ServicePort.of(8080));
    }

    public static IngressSpec createIngressSpec(final ServicePort servicePort) {
        final var canonical = IngressRule.of("domain.tld", "test", servicePort);
        final var www = IngressRule.of("www.domain.tld", "test", servicePort);
        final var tls = IngressTLS.of("test-tls", "domain.tld", "www.domain.tld");
        return IngressSpec.of(tls, canonical, www);
    }

}
