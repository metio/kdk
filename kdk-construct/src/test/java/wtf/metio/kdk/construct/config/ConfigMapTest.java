/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.construct.config;

import wtf.metio.kdk.construct.ConstructTCK;
import wtf.metio.kdk.construct.ConstructWithBuilderTCK;

import java.util.Map;
import java.util.stream.Stream;

class ConfigMapTest implements ConstructTCK<ConfigMap>, ConstructWithBuilderTCK<ImmutableConfigMap.Builder> {

    static Stream<ConfigMap> types() {
        return Stream.of(
                ConfigMap.builder().build(),
                ConfigMap.builder().putData("key", "value").build(),
                ConfigMap.builder().putBinaryData("key", "value").build(),
                ConfigMap.builder().data(Map.of()).build(),
                ConfigMap.builder().binaryData(Map.of()).build()
        );
    }

    @Override
    public ImmutableConfigMap.Builder builder() {
        return ConfigMap.builder();
    }

}
