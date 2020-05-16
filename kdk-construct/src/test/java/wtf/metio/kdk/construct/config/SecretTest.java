/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import wtf.metio.kdk.construct.ConstructTCK;
import wtf.metio.kdk.construct.ConstructWithBuilderTCK;
import wtf.metio.kdk.tests.Secrets;

import java.util.stream.Stream;

class SecretTest implements ConstructTCK<Secret>, ConstructWithBuilderTCK<ImmutableSecret.Builder> {

    static Stream<Secret> types() {
        return Stream.of(
                Secrets.testSecret(),
                Secrets.dockerconfigjson(),
                Secrets.testSecretWithMetadata()
        );
    }

    @Override
    public ImmutableSecret.Builder builder() {
        return Secret.builder();
    }

}
