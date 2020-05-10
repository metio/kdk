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
import wtf.metio.kdk.stack.Stack;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface ValidationTCK {

    @DisplayName("detect no error in valid stacks")
    @ParameterizedTest(name = "[{index}]")
    @MethodSource("validStacks")
    default void shouldDetectNoErrors(final Stack stack, final Validators validators) {
        final var errors = Validate.validate(stack, validators);
        assertTrue(errors.isEmpty(), formatErrors(errors));
    }

    @DisplayName("detect error(s) in invalid stacks")
    @ParameterizedTest(name = "[{index}]")
    @MethodSource("invalidStacks")
    default void shouldDetectErrors(final Stack stack, final Validators validators) {
        final var errors = Validate.validate(stack, validators);
        assertFalse(errors.isEmpty(), formatErrors(errors));
    }

    private static Supplier<String> formatErrors(final List<ValidationError> errors) {
        return () -> errors.stream()
                .map(error -> String.format("%s/%s", resourceName(error), error.errorId()))
                .collect(Collectors.joining());
    }

    private static String resourceName(final ValidationError error) {
        return error.resource().getClass().getInterfaces()[0].getSimpleName();
    }

}
