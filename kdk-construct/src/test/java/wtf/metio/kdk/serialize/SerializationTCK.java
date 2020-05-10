/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.serialize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wtf.metio.kdk.stack.Stack;
import wtf.metio.kdk.tests.Deployments;
import wtf.metio.kdk.tests.Ingresses;
import wtf.metio.kdk.tests.Secrets;
import wtf.metio.kdk.tests.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static wtf.metio.kdk.serialize.Serialize.asYaml;

public interface SerializationTCK {

    String basePath();

    @DisplayName("produce expected YAML")
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("yamlAndStack")
    default void shouldProduceExpectedYaml(final String fileName, final Stack stack) throws IOException {
        // given
        final var expectedYaml = Files.readString(Paths.get(basePath() + fileName));

        // when
        final var yaml = asYaml(stack);

        // then
        assertEquals(expectedYaml, yaml);
    }

}
