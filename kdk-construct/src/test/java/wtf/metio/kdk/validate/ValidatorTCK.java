/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wtf.metio.kdk.construct.config.Secret;
import wtf.metio.kdk.stack.Stack;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public interface ValidatorTCK<TYPE> {

    Function<TYPE, Stream<ValidationError>> validator();

    @DisplayName("detect no error in valid objects")
    @ParameterizedTest(name = "[{index}]")
    @MethodSource("noErrors")
    default void shouldDetectNoError(final TYPE object) {
        final var errors = validator().apply(object);
        assertEquals(0, errors.count());
    }

    @DisplayName("detect error(s) in invalid objects")
    @ParameterizedTest(name = "[{index}]")
    @MethodSource("errors")
    default void shouldDetectError(final TYPE object) {
        final var errors = validator().apply(object);
        assertEquals(1, errors.count());
    }

}
