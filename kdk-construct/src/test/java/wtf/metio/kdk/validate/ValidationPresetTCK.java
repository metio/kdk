/*
 * This file is part of kdk. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of kdk,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */

package wtf.metio.kdk.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface ValidationPresetTCK<TYPE> {

    Supplier<List<Function<TYPE, Stream<ValidationError>>>> allPreset();

    Supplier<List<Function<TYPE, Stream<ValidationError>>>> recommendedPreset();

    @Test
    default void shouldDefineAllPreset() {
        Assertions.assertNotNull(allPreset().get());
    }

    @Test
    default void shouldDefineRecommendedPreset() {
        Assertions.assertNotNull(recommendedPreset().get());
    }

}
