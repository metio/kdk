/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.junit.jupiter.params.provider.Arguments;
import wtf.metio.kdk.construct.config.ConfigMap;
import wtf.metio.kdk.construct.service.Service;
import wtf.metio.kdk.construct.service.ServiceSpec;
import wtf.metio.kdk.serialize.SerializationTCK;
import wtf.metio.kdk.stack.Stack;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class ServiceSerializationTest implements SerializationTCK {

    @Override
    public String basePath() {
        return "src/test/resources/wtf/metio/kdk/serialize/service/ServiceTest/";
    }

    static Stream<Arguments> yamlAndStack() {
        return Stream.of(
                arguments("simpleService.yaml", asStack(simpleService()))
        );
    }

    private static Service simpleService() {
        return Service.builder()
                .spec(ServiceSpec.builder().build())
                .build();
    }

    private static Stack asStack(final Service service) {
        return Stack.builder()
                .addService(service)
                .build();
    }

}
