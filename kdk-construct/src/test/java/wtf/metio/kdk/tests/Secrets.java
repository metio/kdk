/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.tests;

import wtf.metio.kdk.construct.meta.ObjectMeta;
import wtf.metio.kdk.construct.config.Secret;

public final class Secrets {

    public static Secret testSecret() {
        return Secret.builder()
                .type("test")
                .build();
    }

    public static Secret testSecretWithMetadata() {
        return Secret.builder()
                .metadata(ObjectMetas.createObjectMeta())
                .type("test")
                .build();
    }

    public static Secret dockerconfigjson() {
        return Secret.builder()
                .type("kubernetes.io/dockerconfigjson")
                .putData(".dockerconfigjson", "some-super-secret-stuff")
                .metadata(ObjectMeta.builder()
                        .name("test")
                        .namespace("example")
                        .build())
                .build();
    }

    private Secrets() {
        // factory class
    }

}
