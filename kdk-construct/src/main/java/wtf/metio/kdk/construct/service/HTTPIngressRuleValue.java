/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.service;

import org.immutables.value.Value;

import java.util.List;

/**
 * @see <a href="https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.17/#httpingressrulevalue-v1beta1-networking-k8s-io">k8s spec</a>
 */
@Value.Immutable
public interface HTTPIngressRuleValue {

    //region Builders
    static ImmutableHTTPIngressRuleValue.Builder builder() {
        return ImmutableHTTPIngressRuleValue.builder();
    }

    static HTTPIngressRuleValue httpIngressRuleValue(final HTTPIngressPath path) {
        return ImmutableHTTPIngressRuleValue.builder().addPaths(path).build();
    }
    //endregion

    List<HTTPIngressPath> paths();

}
