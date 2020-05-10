/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecretTest {

    @Test
    void shouldHaveBuilder() {
        assertNotNull(Secret.builder());
    }

    @Test
    void shouldCreateSecret() {
        assertNotNull(Secret.builder().type("kubernetes.io/dockerconfigjson").build());
    }

}
