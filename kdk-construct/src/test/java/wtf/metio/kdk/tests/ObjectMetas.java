/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.meta.ImmutableObjectMeta;
import wtf.metio.kdk.construct.meta.ObjectMeta;

import static wtf.metio.kdk.construct.meta.Label.*;
import static wtf.metio.kdk.construct.meta.Label.k8sManagedBy;

public final class ObjectMetas {

    public static ImmutableObjectMeta createObjectMeta() {
        return ObjectMeta.builder()
                .name("test")
                .namespace("example")
                .addLabels(of("branch", "master"))
                .addLabels(of("commit", "d9901dde1afad491eec589a5c5264679fdb17d20"))
                .addLabels(helmChart("test"))
                .addLabels(k8sName("test"))
                .addLabels(k8sVersion("1.2.3"))
                .addLabels(k8sComponent("backend"))
                .addLabels(k8sPartOf("conan"))
                .addLabels(k8sInstance("eu-west-17"))
                .addLabels(k8sManagedBy("kdk"))
                .build();
    }

    private ObjectMetas() {
        // factory class
    }

}
